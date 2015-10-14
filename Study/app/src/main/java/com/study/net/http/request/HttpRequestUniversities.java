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
public class HttpRequestUniversities {

    private static HttpRequestUniversities instance;

    public static HttpRequestUniversities instance() {

        if (instance == null)
            instance = new HttpRequestUniversities();
        return instance;
    }

    public void RequestUniversities(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.UniversitiesBaseUrl, Fields, Includes, null);
            LogUtils.i("RequestUniversities url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

    public void RequestUniversities(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> Ids) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.UniversitiesBaseUrl, Fields, Includes, Ids);
            LogUtils.i("RequestUniversities url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

}
