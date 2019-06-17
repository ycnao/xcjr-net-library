package com.nadia.totoro.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nadia.totoro.sample.common.net.RequestTest
import com.xcjr.lib.net.http.HttpRequestCallback
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btn.setOnClickListener {
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
}
