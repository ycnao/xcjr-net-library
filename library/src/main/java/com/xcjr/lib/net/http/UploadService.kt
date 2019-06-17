package com.xcjr.lib.net.http

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.PartMap
import retrofit2.http.POST
import retrofit2.http.Multipart
import rx.Observable


/**
 * 网络请求
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
interface UploadService {

	@Multipart
	@POST("/api")
	fun uploadFile(@Part("action") action: RequestBody, @Part("jsonString") jsonString: RequestBody, @Part image: MultipartBody.Part): Call<ResponseBody>


	@Multipart
	@POST("/api")
	fun uploadFiles(@Part("action") action: RequestBody, @Part("jsonString") jsonString: RequestBody, @PartMap maps: Map<String, RequestBody>): Observable<ResponseBody>
}
