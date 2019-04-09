package com.yuhua.basemodule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * BaseRecyclerAdapter
 * Create By: 俞华
 * Date: 2019/03/22
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerHolder> {
    private Context context;
    private List<T> data;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(layoutId(), parent, false);
        BaseRecyclerHolder holder = new BaseRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.BaseRecyclerHolder holder, int position) {
        bindData(holder, data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;

        public BaseRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        public View getView(int id) {
            View view = views.get(id);
            if (null == view) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return view;
        }
    }

    /**
     * 设置item的id
     *
     * @return
     */
    public abstract int layoutId();

    /**
     * 设置数据
     *
     * @param holder
     * @param t
     * @return
     */
    public abstract void bindData(BaseRecyclerHolder holder, T t, int position);

}
