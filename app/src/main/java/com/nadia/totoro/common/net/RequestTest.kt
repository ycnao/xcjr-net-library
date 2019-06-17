package com.nadia.totoro.common.net

import android.content.Context
import com.xcjr.lib.net.http.HttpRequestCallback
import org.json.JSONObject

/**
 * 测试
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
class RequestTest(private val mContext: Context) : RequestAbstract() {

	/**
	 * 密码登录
	 */
	fun test(mobile: String, password: String, callback: HttpRequestCallback) {
		val jsonObject = JSONObject()
		jsonObject.put("name", mobile)
		jsonObject.put("password", password)
		request(mContext, "${"网络服务"}.login", jsonObject, callback)
	}

}
