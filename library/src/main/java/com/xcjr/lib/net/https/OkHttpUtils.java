package com.xcjr.lib.net.https;

import android.annotation.SuppressLint;

import com.xcjr.lib.net.util.MD5Util;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
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
public class OkHttpUtils {

    public OkHttpUtils() {
    }

    public static JSONObject getHeader(String appId, String appSecret, String encrypt, String systemSourceSecretKey) {
        JSONObject header = new JSONObject();
        try {
            String token = new MD5Util().getMD5Str(appId.concat(appSecret));
            header.put("token", token);
            header.put("encrypt", encrypt);
            header.put("systemSourceSecretKey", systemSourceSecretKey);
            return header;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SSLSocketFactory sSLSocketFactory = null;

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    public static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sSLSocketFactory;
    }

    public static class TrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class TrustAllHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
