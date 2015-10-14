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
public class HttpRequestCourses {

    private static HttpRequestCourses instance;

    public static HttpRequestCourses instance() {

        if (instance == null)
            instance = new HttpRequestCourses();

        return instance;
    }

    public void RequestQueryCourses(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> values) {
        if (callBack != null) {
            String url = HttpConfig.GetQueryUrl(HttpConfig.CoursesBaseUrl, Fields, Includes, values);
            LogUtils.i("RequestQueryCourses url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

    public void RequestCourses(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.CoursesBaseUrl, Fields, Includes, null);
            LogUtils.i("RequestCourses url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

    public void RequestCourses(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> Ids) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.CoursesBaseUrl, Fields, Includes, Ids);
            LogUtils.i("RequestCourses url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

}
