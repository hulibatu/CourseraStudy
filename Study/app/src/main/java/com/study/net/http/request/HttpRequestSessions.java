package com.study.net.http.request;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.study.net.http.HttpConfig;

import java.util.ArrayList;

/**
 * Created by hugo on 15/10/7.
 */
public class HttpRequestSessions {

    private static HttpRequestSessions instance;

    public static HttpRequestSessions instance() {

        if (instance == null)
            instance = new HttpRequestSessions();

        return instance;
    }

    public void RequestSessions(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.SessionsBaseUrl, Fields, Includes, null);
            LogUtils.i("RequestSessions url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

    public void RequestSessions(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> Ids) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.SessionsBaseUrl, Fields, Includes, Ids);
            LogUtils.i("RequestSessions url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

}
