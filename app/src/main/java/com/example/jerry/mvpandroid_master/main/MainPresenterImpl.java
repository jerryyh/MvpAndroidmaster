package com.example.jerry.mvpandroid_master.main;

import com.example.jerry.mvpandroid_master.SubjectPostApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * Created by jerry on 2018/6/28.
 */
public class MainPresenterImpl implements MainPresenter, MainInteractor.OnGetDataResultFinishListener {
    private MainView mainView;
    private MainInteractorImpl mainInteractor;

    public MainPresenterImpl(MainView mainView, MainInteractorImpl mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onNext(String resulte, String mothead) {
        if (mainView != null) {
            mainView.onNext(resulte, mothead);
            mainView.dialogDissmiss();
        }
    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public void onResume(MainActivity mainActivity) {
        if (mainView != null) mainView.showLoadingDialog();
        SubjectPostApi postEntity = new SubjectPostApi();
        postEntity.setStart(0);
        postEntity.setAreaId("");
        postEntity.setMothed("SSLJ_CORE_PLATFORM/circles/datatable" + "/" + 0);
        mainInteractor.getDataResult(this, postEntity, mainActivity);
    }

    @Override
    public void onDestory() {
        mainView = null;
    }

    @Override
    public void loadMore(MainActivity mainActivity) {
        SubjectPostApi postEntity = new SubjectPostApi();
        postEntity.setStart(0);
        postEntity.setAreaId("");
        postEntity.setMothed("SSLJ_CORE_PLATFORM/circles/datatable" + "/" + 0);
        mainInteractor.getDataResult(this, postEntity, mainActivity);
    }
}
