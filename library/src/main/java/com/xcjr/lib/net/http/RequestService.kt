package com.xcjr.lib.net.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
 * 网络请求
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
interface RequestService {

    @GET("/api")
    fun getCall(@Query("action") action: String, @Query("jsonString") jsonString: String): Call<ResponseBody>

    @POST("/api")
    fun postCall(@Query("action") action: String, @Query("jsonString") jsonString: String): Call<ResponseBody>

    @GET("/api")
    fun getObservable(@Query("action") action: String, @Query("jsonString") jsonString: String): Observable<ResponseBody>

    @POST("/api")
    fun postObservable(@Query("action") action: String, @Query("jsonString") jsonString: String): Observable<ResponseBody>

    @GET("/geocoder")
    fun geoCoder(@Query("address") address: String, @Query("output") output: String): Call<ResponseBody>

}
