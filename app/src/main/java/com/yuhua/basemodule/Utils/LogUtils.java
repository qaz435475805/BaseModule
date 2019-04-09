package com.yuhua.basemodule.Utils;

import android.util.Log;

/**
 * Create By: 俞华
 * Date: 2019/03/05
 */
public class LogUtils {
    //可以全局控制是否打印log日志
    private static boolean isPrintLog = Utils.isDebug();
    private static String TAG = "------LogUtils";

    public static void v(String msg) {
        if (isPrintLog) {
            v(TAG, msg);
        }
    }

    public static void v(String tagName, String msg) {
        if (isPrintLog) {
            Log.v(tagName, msg);
        }
    }

    public static void d(String msg) {
        if (isPrintLog) {
            d(TAG, msg);
        }
    }

    public static void d(String tagName, String msg) {
        if (isPrintLog) {
            Log.d(tagName, msg);
        }
    }

    public static void i(String msg) {
        if (isPrintLog) {
            i(TAG, msg);
        }
    }

    public static void i(String tagName, String msg) {
        if (isPrintLog) {
            Log.i(tagName, msg);
        }
    }

    public static void w(String msg) {
        if (isPrintLog) {
            w(TAG, msg);
        }
    }

    public static void w(String tagName, String msg) {
        if (isPrintLog) {
            Log.w(tagName, msg);
        }
    }

    public static void e(String msg) {
        if (isPrintLog) {
            e(TAG, msg);
        }
    }

    public static void e(String tagName, String msg) {
        if (isPrintLog) {
            Log.e(tagName, msg);
        }
    }

}
