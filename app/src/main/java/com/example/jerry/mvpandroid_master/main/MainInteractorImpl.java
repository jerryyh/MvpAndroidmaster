package com.example.jerry.mvpandroid_master.main;
import android.os.Handler;

import com.example.jerry.mvpandroid_master.SubjectPostApi;
import com.google.gson.Gson;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.ArrayList;
import java.util.List;

/**
 *  model 具体业务逻辑的实现
 * Created by jerry on 2018/6/28.
 */
public class MainInteractorImpl implements MainInteractor,HttpOnNextListener {
    @Override
    public void getDataResult(final OnGetDataResultFinishListener listener, SubjectPostApi baseApi,MainActivity rxAppCompatActivity) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Gson gson = new Gson();
                String str = gson.toJson(createSnsCardList());
                listener.onNext(str,"");
            }
        }, 2000);
//        new HttpManager(this,rxAppCompatActivity).doHttpDeal(baseApi);
    }
    private List<SnsCard> createSnsCardList() {
        List<SnsCard> SnsCards=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SnsCards.add(new SnsCard("jerry"+i));
        }
        return SnsCards;
    }

    @Override
    public void onNext(String resulte, String mothead) {

    }

    @Override
    public void onError(ApiException e) {

    }
}
