package com.example.jerry.mvpandroid_master.main;

import android.os.Handler;
import android.os.RemoteException;

import com.example.jerry.mvpandroid_master.IMyAidl;
import com.example.jerry.mvpandroid_master.SubjectApi;
import com.example.jerry.mvpandroid_master.bean.Person;
import com.google.gson.Gson;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * model 具体业务逻辑的实现
 * Created by jerry on 2018/6/28.
 */
public class MainInteractorImpl implements MainInteractor, HttpOnNextListener {
    @Override
    public void getServiceData(IMyAidl mAidl,final OnGetDataResultFinishListener listener) {
        Random random = new Random();
        Person person = new Person("jerry" + random.nextInt(10));
        try {
            mAidl.addPerson(person);
            List<Person> personList = mAidl.getPersonList();
            listener.showText(personList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getDataResult(final OnGetDataResultFinishListener listener, SubjectApi baseApi, MainActivity rxAppCompatActivity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String str = gson.toJson(createSnsCardList());
                listener.onNext(str, "");
            }
        }, 2000);
//        new HttpManager(this,rxAppCompatActivity).doHttpDeal(baseApi);
    }

    private List<SnsCard> createSnsCardList() {
        List<SnsCard> SnsCards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SnsCards.add(new SnsCard("jerry" + i));
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
