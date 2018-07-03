package com.example.jerry.mvpandroid_master.main;

import android.content.ComponentName;

import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.jerry.mvpandroid_master.IMyAidl;
import com.example.jerry.mvpandroid_master.R;
import com.example.jerry.mvpandroid_master.SoocketActivity;
import com.example.jerry.mvpandroid_master.databinding.ActivityMainTestBinding;
import com.example.jerry.mvpandroid_master.service.MyAidlService;
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
    private ActivityMainTestBinding binding;
    private IMyAidl mAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_test);
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
        binding.title.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.getServiceData(mAidl);
            }
        });
        binding.buttonSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SoocketActivity.class);
                startActivity(intent);
            }
        });
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };

    private void initData() {
        mainPresenter = new MainPresenterImpl(this, new MainInteractorImpl());
        mainPresenter.onResume(this);
        Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void showLoadingDialog() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dialogDissmiss() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(String resulte, String mothead) {
        Gson gson = new Gson();
        List<SnsCard> items = gson.fromJson(resulte, new TypeToken<List<SnsCard>>() {
        }.getType());
        adapter.addAll(items);
    }

    @Override
    public void onError(ApiException e) {
    }

    @Override
    public void showText(String s) {
        binding.tvName.setText(s);
    }

    @Override
    public void onLoadMore() {
        mainPresenter.loadMore(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mainPresenter.onDestory();
    }
}
