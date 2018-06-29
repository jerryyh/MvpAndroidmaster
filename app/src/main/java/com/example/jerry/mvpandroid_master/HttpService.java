package com.example.jerry.mvpandroid_master;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface HttpService {

    @GET("SSLJ_CORE_PLATFORM/circles/datatable")
    Observable<String> getDatatable(@Query("start") int start,
                                    @Query("areaId") String areaId);

}
