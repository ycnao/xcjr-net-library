package com.nadia.totoro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nadia.totoro.common.net.RequestTest
import com.xcjr.lib.net.http.HttpRequestCallback

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    RequestTest(this).test("", "", object : HttpRequestCallback {
      override fun notOpenLocalNet() {
      }

      override fun onStart() {
      }

      override fun onFailure(description: String) {
      }

      override fun onSuccess(responseJsonString: String) {
      }

      override fun onFinish() {
      }
    })
  }
}
