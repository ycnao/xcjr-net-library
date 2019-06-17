package com.nadia.totoro.common.net

import android.content.Context
import android.util.Log
import com.nadia.totoro.common.app.Constants
import com.xcjr.lib.net.http.HttpRequestCallback
import com.xcjr.lib.net.RequestAPIAbstract
import com.xcjr.lib.net.https.OkHttpUtils
import com.xcjr.lib.net.util.Base64Util
import org.json.JSONObject
import java.io.File

/**
 * 网络请求封装
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
abstract class RequestAbstract : RequestAPIAbstract() {

    /**
     * 网络请求
     */
    fun request(context: Context, action: String, jsonObject: JSONObject, callback: HttpRequestCallback) {
        //检测本地网络是否开启。
        Log.d("请求服务端", jsonObject.toString())

        //加密
        val base64 = Base64Util().getBase64(jsonObject.toString())
        requestServerByJsonObject(Constants.REQUEST_SERVER, action, base64, callback)
    }

    /**
     * 上传
     */
    fun upload(context: Context, action: String, jsonObject: JSONObject, file: File, callback: HttpRequestCallback) {

        //加密
        val base64 = Base64Util().getBase64(jsonObject.toString())
        val token = Base64Util().getBase64(OkHttpUtils.getHeader()!!.toString())
        uploadByJsonObject(Constants.UPLOAD_SERVER, action, base64, file, token, callback)
    }
}
