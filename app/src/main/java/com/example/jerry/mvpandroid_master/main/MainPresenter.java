package com.example.jerry.mvpandroid_master.main;

import com.example.jerry.mvpandroid_master.IMyAidl;

/**
 * Created by jerry on 2018/6/28.
 */
public interface MainPresenter {
    void onResume(MainActivity mainActivity);
    void onDestory();
    void loadMore(MainActivity mainActivity);
    void getServiceData(IMyAidl mAidl);
}
