package com.example.jerry.mvpandroid_master.main;

/**
 * Created by jerry on 2018/6/28.
 */
public interface MainPresenter {
    void onResume(MainActivity mainActivity);
    void onDestory();
    void loadMore(MainActivity mainActivity);
}
