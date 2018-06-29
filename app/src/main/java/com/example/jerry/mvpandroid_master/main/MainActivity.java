package com.example.jerry.mvpandroid_master.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.jerry.mvpandroid_master.R;
import com.example.jerry.mvpandroid_master.databinding.ActivityMainTestBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

public class MainActivity extends RxAppCompatActivity implements MainView, RecyclerArrayAdapter.OnLoadMoreListener {
    private LinearLayoutManager linearLayoutManager;
    private SnsBlogAdapter adapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        ActivityMainTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_test);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter = new SnsBlogAdapter(this));
        adapter.setMore(R.layout.view_more, this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
    }

    private void initData() {
        mainPresenter = new MainPresenterImpl(this, new MainInteractorImpl());
        mainPresenter.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestory();
    }

    @Override
    public void onLoadMore() {
        mainPresenter.loadMore(this);
    }

    @Override
    public void onNext(String resulte, String mothead) {
        Gson gson=new Gson();
        List<SnsCard> items = gson.fromJson(resulte, new TypeToken<List<SnsCard>>() {}.getType());
        adapter.addAll(items);
    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public void showLoadingDialog() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dialogDissmiss() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }
}
