package com.example.jerry.mvpandroid_master.main;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jerry.mvpandroid_master.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by jerry on 2018/6/29.
 */
public class StatusViewHolder extends BaseViewHolder<SnsCard> {
    private TextView tv_title;

    public StatusViewHolder(ViewGroup parent) {
        super(parent, R.layout.view_item_sns_state);
        tv_title = $(R.id.tv_user_name);
    }

    @Override
    public void setData(final SnsCard snsCard) {
        tv_title.setText(snsCard.getUserName());
    }
}
