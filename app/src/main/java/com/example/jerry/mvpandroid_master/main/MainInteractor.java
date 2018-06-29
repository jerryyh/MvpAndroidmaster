package com.example.jerry.mvpandroid_master.main;

import com.example.jerry.mvpandroid_master.SubjectPostApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * Created by jerry on 2018/6/28.
 */
public interface MainInteractor {
    interface OnGetDataResultFinishListener{
        /**
         * 成功后回调方法
         *
         * @param resulte
         * @param mothead
         */
        void onNext(String resulte, String mothead);

        /**
         * 失败
         * 失败或者错误方法
         * 自定义异常处理
         *
         * @param e
         */
        void onError(ApiException e);
    }

    void getDataResult(OnGetDataResultFinishListener listenter, SubjectPostApi baseApi, MainActivity rxAppCompatActivity);
}
