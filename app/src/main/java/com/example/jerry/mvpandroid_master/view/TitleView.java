package com.example.jerry.mvpandroid_master.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jerry.mvpandroid_master.R;


/**
 * Created by jerry on 2018/6/25.
 */

public class TitleView extends RelativeLayout {
    private RelativeLayout rl_background;
    private Context context;
    private String mTitleName;
    private Button mLeftBtn;
    private TextView mTitleTv;
    private int mColor;
    private int mTextSize;
    private int mImage;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
        handleTypedArray(context, attrs);
        initData();
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        mLeftBtn = (Button) inflate.findViewById(R.id.left_btn);
        mTitleTv = (TextView) inflate.findViewById(R.id.title_tv);
        rl_background = (RelativeLayout) inflate.findViewById(R.id.rl_background);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        mColor = typedArray.getColor(R.styleable.TitleView_titleBackground, Color.WHITE);
        mTextSize = typedArray.getInt(R.styleable.TitleView_titileTextSize, 14);
        mImage = typedArray.getResourceId(R.styleable.TitleView_iconBack, R.drawable.back);
        mTitleName = typedArray.getString(R.styleable.TitleView_titleName);
        typedArray.recycle();
    }

    private void initData() {
        mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        rl_background.setBackgroundColor(mColor);
        mLeftBtn.setBackgroundResource(mImage);
        mTitleTv.setText(mTitleName);
    }

    public void setLeftButtonListener(OnClickListener listener) {
        mLeftBtn.setOnClickListener(listener);
    }

    public void setTitleText(String title) {
        mTitleTv.setText(title);
    }
}
