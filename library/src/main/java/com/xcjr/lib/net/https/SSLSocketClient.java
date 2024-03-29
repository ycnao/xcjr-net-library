package com.xcjr.lib.net.https;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * author: Created by 闹闹 on 2019/1/31
 * version: 1.0.0
 */
public class SSLSocketClient {
	
	/**
	 * 获取这个SSLSocketFactory
	 * @return
	 */
	public static SSLSocketFactory getSSLSocketFactory() {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, getTrustManager(), new SecureRandom());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 获取TrustManager
	 * @return
	 */
	public static TrustManager[] getTrustManager() {
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[]{};
			}
		}};
		return trustAllCerts;
	}
	
	
	/**
	 * 获取HostnameVerifier
	 * @return
	 */
	public static HostnameVerifier getHostnameVerifier() {
		HostnameVerifier hostnameVerifier = new HostnameVerifier() {
			@Override
			public boolean verify(String s, SSLSession sslSession) {
				return true;
			}
		};
		return hostnameVerifier;
	}
}
