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
public class HttpRequestInstructors {

    private static HttpRequestInstructors instance;

    public static HttpRequestInstructors instance() {

        if (instance == null)
            instance = new HttpRequestInstructors();

        return instance;
    }

    public void RequestInstructors(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.InstructorsBaseUrl, Fields, Includes, null);
            LogUtils.i("RequestInstructors url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

    public void RequestInstructors(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes, ArrayList<String> Ids) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.InstructorsBaseUrl, Fields, Includes, Ids);
            LogUtils.i("RequestInstructors url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

}
