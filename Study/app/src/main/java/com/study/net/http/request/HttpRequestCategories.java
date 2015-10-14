package com.study.net.http.request;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.study.net.http.HttpConfig;

import java.util.ArrayList;

/**
 * Created by hugo on 15/10/7.
 * <p/>
 * 类型接口
 */
public class HttpRequestCategories {

    private static HttpRequestCategories instance;

    public static HttpRequestCategories instance() {

        if (instance == null)
            instance = new HttpRequestCategories();

        return instance;
    }

    public void RequestCategories(RequestCallBack<String> callBack, ArrayList<String> Fields, ArrayList<String> Includes) {
        if (callBack != null) {
            String url = HttpConfig.GetUrl(HttpConfig.CategoriesBaseUrl, Fields, Includes, null);
            LogUtils.i("RequestCategories url : " + url);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET, url, callBack);
        }
    }

}
