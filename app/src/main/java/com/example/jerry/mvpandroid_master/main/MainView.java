package com.example.jerry.mvpandroid_master.main;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * Created by jerry on 2018/6/28.
 */
public interface MainView {
    void onNext(String resulte, String mothead);
    void onError(ApiException e);
    void showLoadingDialog();
    void dialogDissmiss();
}
