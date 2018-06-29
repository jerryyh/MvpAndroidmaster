package com.example.jerry.mvpandroid_master.main;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jerry.mvpandroid_master.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * 帖子列表适配器
 * Created by jerry on 2017/6/28.
 */
public class SnsBlogAdapter extends RecyclerArrayAdapter {
    private Context context;
    private int screenWhith;

    public SnsBlogAdapter(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWhith = wm.getDefaultDisplay().getWidth();
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

    class StatusViewHolder extends BaseViewHolder<SnsCard> {
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
}
