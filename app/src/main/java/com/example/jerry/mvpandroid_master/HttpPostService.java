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

public interface HttpPostService {

    @GET("SSLJ_CORE_PLATFORM/circles/datatable")
    Observable<String> getDatatable(@Query("start") int start,
                                    @Query("areaId") String areaId);
    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
    Observable<String> getAllVedioBy(@Field("once_no") boolean once_no);

    @GET("SSLJ_CORE_PLATFORM/directories/selectCompany")
    Observable<String> getCompany();

    @GET("SSLJ_CORE_PLATFORM/directories/selectDepartment")
    Observable<String> getDepartment(@Query("companyId") String companyId);

    @FormUrlEncoded
    @POST("snsuser/userDetail")
    Observable<String> getUserDetail(
//            @Url String url,
            @Header("User-Agent") String userAgent,
            @Header("Content-Type") String contentType,
            @Field("uuid") String uuid,
            @Field("body") String body,
            @Field("sign") String sign,
            @Field("deviceType") String deviceType,
            @Field("st") String st,
            @Field("clientVersion") String clientVersion,
            @Field("screen") String screen,
            @Field("osVersion") String osVersion,
            @Field("area") String area,
            @Field("longtitude") String longtitude,
            @Field("latitude") String latitude,
            @Field("deviceId") String deviceId,
            @Field("ulongtitude") String ulongtitude,
            @Field("ulatitude") String ulatitude,
            @Field("channel") String channel
    );

    @FormUrlEncoded
    @POST("snsuser/blogsByUser")
    Observable<String> getUserDetailHomeline(
//            @Url String url,
            @Header("User-Agent") String userAgent,
            @Header("Content-Type") String contentType,
            @Field("uuid") String uuid,
            @Field("body") String body,
            @Field("sign") String sign,
            @Field("deviceType") String deviceType,
            @Field("st") String st,
            @Field("clientVersion") String clientVersion,
            @Field("screen") String screen,
            @Field("osVersion") String osVersion,
            @Field("area") String area,
            @Field("longtitude") String longtitude,
            @Field("latitude") String latitude,
            @Field("deviceId") String deviceId,
            @Field("ulongtitude") String ulongtitude,
            @Field("ulatitude") String ulatitude,
            @Field("channel") String channel
    );


}
