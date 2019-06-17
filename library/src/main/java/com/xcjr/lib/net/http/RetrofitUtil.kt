package com.xcjr.lib.net.http

import retrofit2.Retrofit

/**
 *
 * author: Created by 闹闹 on 2018-09-29
 * version: 1.0.0
 */
class RetrofitUtil {

    private var retrofit: Retrofit? = null

    fun <T> createApi(server: String, clazz: Class<T>): T {
        if (retrofit == null) {
            synchronized(RetrofitUtil::class.java) {
                if (retrofit == null) {
                    val builder = Retrofit.Builder()
                    builder.baseUrl(server)
                    //设置数据解析器
                    //                    builder.addConverterFactory(GsonConverterFactory.create());
                    //                    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    retrofit = builder.build()
                }
            }
        }
        return retrofit!!.create(clazz)
    }
}
