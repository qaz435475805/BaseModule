package com.yuhua.basemodule;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuhua.basemodule.Utils.NetUtil;
import com.yuhua.basemodule.Utils.Utils;

public abstract class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetEvevt {

    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;
    private NetBroadcastReceiver netBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        evevt = this;
        initView();
        inspectNet();
        initNetBroadcastReceiver();
    }


    /**
     * 沉浸式
     */
    private void initView() {
        /*
         * 隐藏状态栏和导航栏
         * SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：将布局内容拓展到状态栏的后面，即把布局里的内容扩展到上方的“状态栏”里
         * SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION：将布局内容拓展到导航栏的后面，即把布局里的内容扩展到下方的“导航栏”里
         * SYSTEM_UI_FLAG_LAYOUT_STABLE：稳定布局，主要是在全屏和非全屏切换时，布局不要有大的变化，和SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN一起用
         * SYSTEM_UI_FLAG_HIDE_NAVIGATION：隐藏导航栏（直接隐藏整个导航栏）
         * SYSTEM_UI_FLAG_FULLSCREEN：隐藏状态栏（直接隐藏整个状态栏）
         */
        if (Utils.isAndroid5()) {
            getWindow().getDecorView().setSystemUiVisibility(ViewGroup.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
//        隐藏ActionBar
        if (getActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    /**
     * 初始化时判断有没有网络
     */
    private void inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        if (!isNetConnect()) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    private void initNetBroadcastReceiver() {
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//网络变化的广播
            registerReceiver(netBroadcastReceiver, intentFilter);
        }
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netMobile
     */
    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
        if (!isNetConnect()) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断有无网络
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }
}
