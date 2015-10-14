package com.study.util;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by hugo on 15/10/13.
 */
public class BitmapHelp {

    private BitmapHelp() {
    }

    private static BitmapUtils bitmapUtils;

    /**
     * BitmapUtils不是单例的 根据需要重载多个获取实例的方法
     *
     * @param appContext application context
     * @return
     */
    public static BitmapUtils getBitmapUtils(Context appContext) {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(appContext);
        }
        return bitmapUtils;
    }

}
