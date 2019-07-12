package com.xcjr.lib.net

import android.util.Log
import com.google.gson.Gson
import com.xcjr.lib.net.model.DataBean
import com.xcjr.lib.net.http.HttpRequestCallback
import com.xcjr.lib.net.http.RequestService
import com.xcjr.lib.net.http.RetrofitUtil
import com.xcjr.lib.net.https.OkHttpUtils
import com.xcjr.lib.net.model.ResponseJsonBean
import com.xcjr.lib.net.http.UploadService
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 *网络请求封装
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
abstract class RequestAPIAbstract {

    /**
     * 网络请求
     */
    fun requestServerByJsonObject(httpSever: String, action: String, json: String, callback: HttpRequestCallback) {
        Log.d("请求服务端", json)
        requestServer(httpSever, action, json, callback)
    }

    /**
     * 网络请求
     *
     * @param act      请求方法名称
     * @param json     请求参数封装
     * @param callback 返回值处理
     */
    private fun requestServer(host: String, act: String, json: String, callback: HttpRequestCallback) {
        val url = "url=$host/api?action=$act&jsonString=$json"

        val httpService = RetrofitUtil().createApi(host, RequestService::class.java)
        val response = httpService.getCall(act, json)
        callback.onStart()
        response.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("请求服务端/成功 URL", url)
                try {
                    Log.d("请求服务端成功返回response", response.toString())
                    if (response.body() != null) {
                        val jsonData = response.body()!!.string()
                        val responseJsonBean = Gson().fromJson(jsonData, ResponseJsonBean::class.java)
                        if (responseJsonBean.isSuccess) {
                            Log.d("请求服务端成功返回jsonData", "" + if (response.body() != null) jsonData else "null")
                            callback.onSuccess(jsonData)
                        } else {
                            Log.d("请求服务端失败返回jsonData", "" + if (response.body() != null) jsonData else "null")
                            callback.onFailure(responseJsonBean.description ?: "服务器异常")
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                callback.onFinish()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                callback.onFinish()
                Log.d("请求服务端/失败 URL", url)
                if (t != null) Log.d("请求服务端失败返回Throwable：", t.message)
                if (t!!.message == "timeout") {
                    callback.onFailure("请求超时")
                } else {
                    callback.onFailure("网络异常")
                }
            }
        })
    }

    /**
     * 上传
     */
    fun uploadByJsonObject(
        uploadServer: String,
        action: String,
        json: String,
        file: File,
        token: String,
        callback: HttpRequestCallback
    ) {
        Log.d("请求服务端", json)
        uploadServer(uploadServer, action, json, file, token, callback)
    }

    /**
     * 网络请求 base64上传
     *
     * @param act      请求方法名称
     * @param json     请求参数封装
     * @param file     文件名
     * @param callback 返回值处理
     */
    private var okHttpClient: OkHttpClient? = null

    private fun uploadServer(
        host: String,
        act: String,
        json: String,
        file: File,
        token: String,
        callback: HttpRequestCallback
    ) {
        val url = "url=$host/api?action=$act&jsonString=$json"

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val part = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val client = OkHttpClient.Builder()
        try {
            okHttpClient = client.hostnameVerifier(OkHttpUtils.TrustAllHostnameVerifier())
                .sslSocketFactory(OkHttpUtils.createSSLSocketFactory())
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("token", token).build()
                    chain.proceed(request)
                }.build()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //创建服务请求
        val builder = Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
        val uploadService = builder.create(UploadService::class.java)
        val response = uploadService.uploadFile(toRequestBody(act), toRequestBody(json), part)
        callback.onStart()
        response.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("请求服务端/成功 URL", url)
                try {
                    Log.d("请求服务端成功返回response", response.toString())
                    if (response.body() != null) {
                        val jsonData = response.body()!!.string()
                        val responseJsonBean = Gson().fromJson(jsonData, ResponseJsonBean::class.java)
                        if (responseJsonBean != null) {
                            if (responseJsonBean.isSuccess) {
                                Log.d("请求服务端成功返回jsonData", "" + if (response.body() != null) jsonData else "null")
                                callback.onSuccess(jsonData)
                            } else {
                                Log.d("请求服务端失败返回jsonData", "" + if (response.body() != null) jsonData else "null")
                                callback.onFailure(responseJsonBean.description)
                            }
                        } else {
                            callback.onFailure("文件上传失败，请重新上传！")
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                callback.onFinish()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onFinish()
                Log.d("请求服务端/失败 URL", url)
                Log.d("请求服务端失败返回Throwable：", t.message)
                callback.onFailure("网络异常")
            }
        })
    }

    private fun toRequestBody(value: String): RequestBody = RequestBody.create(MediaType.parse("text/plain"), value)

    fun doServer(host: String, address: String, callback: HttpRequestCallback) {
        val url = "url=$host/geocoder/?address=$address&output=json"
        val httpService = RetrofitUtil().createApi(host, RequestService::class.java)
        val response = httpService.geoCoder(address, "json")
        callback.onStart()
        response.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("请求服务端/成功 URL", url)
                try {
                    Log.d("请求服务端成功返回response", response.toString())
                    if (response.body() != null) {
                        val jsonData = response.body()!!.string()
                        val responseJsonBean = Gson().fromJson(jsonData, DataBean::class.java)
                        if (responseJsonBean.status == "OK") {
                            Log.d("请求服务端成功返回jsonData", "" + if (response.body() != null) jsonData else "null")
                            callback.onSuccess(jsonData)
                        } else {
                            Log.d("请求服务端失败返回jsonData", "" + if (response.body() != null) jsonData else "null")
                            callback.onFailure("失败")
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                callback.onFinish()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                callback.onFinish()
                Log.d("请求服务端/失败 URL", url)
                if (t != null) Log.d("请求服务端失败返回Throwable：", t.message)
                if (t!!.message == "timeout") {
                    callback.onFailure("请求超时")
                } else {
                    callback.onFailure("网络异常")
                }
            }
        })
    }
}
