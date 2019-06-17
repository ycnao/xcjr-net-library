package com.xcjr.lib.net.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 创建https请求中要用到的证书管理类x509trustmanager
 * author: Created by 闹闹 on 2019/1/31
 * version: 1.0.0
 */
public class SSLTrustAllManager implements X509TrustManager {
	
	@Override
	public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
	
	}
	
	@Override
	public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
	
	}
	
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}
}
