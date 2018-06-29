package com.example.jerry.mvpandroid_master.main;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * 帖子列表适配器
 * Created by jerry on 2017/6/28.
 */
public class SnsBlogAdapter extends RecyclerArrayAdapter {
    private Context context;

    public SnsBlogAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getViewType(int position) {
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new StatusViewHolder(parent);
    }
}
