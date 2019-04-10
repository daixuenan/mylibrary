package com.dai.gallery.network.base;

import android.text.TextUtils;

import com.dai.gallery.AppApplication;
import com.dai.gallery.utils.Configure;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyStore;

/**
 * Created by dai on 2018/11/19.
 */

public class AsyncApiClient {

    //    public static void get(String url, AsyncHttpResponseHandler asyncHttpResponseHandler) {
//        AsyncHttpClient client = getNewHttpClient();
//        addHeaders(client);
//        try {
//            client.get(AppApplication.getInstance(), url, null, "application/json", asyncHttpResponseHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void get(String url, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpClient client = getNewHttpClient();
        addHeaders(client);
        try {
            client.get(url, asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(HttpEntity entity, String url, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpClient client = getNewHttpClient();
        addHeaders(client);
        try {
            client.post(AppApplication.getInstance(), url, entity, "application/json", asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpClient client = getNewHttpClient();
        addHeaders(client);
        try {
            client.post(url, params, asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void put(HttpEntity entity, String url, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpClient client = getNewHttpClient();
        addHeaders(client);
        try {
            client.put(AppApplication.getInstance(), url, entity, "application/json", asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(HttpEntity entity, String url, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        AsyncHttpClient client = getNewHttpClient();
        addHeaders(client);
        try {
            client.delete(AppApplication.getInstance(), url, entity, "application/json", asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addHeaders(AsyncHttpClient client) {
        if (!TextUtils.isEmpty(Configure.TOKEN)) {
            client.addHeader("Authorization", Configure.TOKEN);
        }
        if (!TextUtils.isEmpty(Configure.APP_VERSION)) {
            client.addHeader("appVersion", Configure.APP_VERSION);
        }
        if (!TextUtils.isEmpty(Configure.DEVICE_TOKEN)) {
            client.addHeader("DEVICE_ID", Configure.DEVICE_TOKEN);
        }
        client.addHeader("PHONE_MODEL", android.os.Build.MODEL);
        client.addHeader("APP_VERSION", Configure.APP_VERSION);
        client.addHeader("SYSTEM_VERSION", android.os.Build.VERSION.RELEASE);
        client.addHeader("DEVICE_TYPE", "Android");
    }

    // 可以访问https
    public static AsyncHttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory sf = new SSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            // 设置网络超时为10秒
            params.setIntParameter("http.socket.timeout", 10 * 1000);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
            return new AsyncHttpClient(registry);
        } catch (Exception e) {
            return new AsyncHttpClient();
        }
    }
}
