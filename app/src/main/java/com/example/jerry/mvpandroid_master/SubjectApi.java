package com.example.jerry.mvpandroid_master;


import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 测试数据
 * Created by Jerry on 2017/4/6.
 */
public class SubjectApi extends BaseApi {
    //接口需要传入的参数 可自定义不同类型
    /*任何你先要传递的参数*/
    private int start;

    private String areaId;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 默认初始化需要给定回调和rx周期类
     * 可以额外设置请求设置加载框显示，回调等（可扩展）
     * 设置可查看BaseApi
     */
    public SubjectApi() {
        setShowProgress(true);
        setCancel(true);
//        setMothed("SSLJ_CORE_PLATFORM/circles/datatable");
        setCache(true);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService service = retrofit.create(HttpService.class);
        return service.getDatatable(getStart(), getAreaId());
    }
}
