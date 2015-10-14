package com.study;

import android.app.Application;
import android.content.Intent;

import com.study.activity.WelcomeActivity;

/**
 * Created by hugo on 15/10/6.
 */
public class StudyApp extends Application implements Thread.UncaughtExceptionHandler{

    private static StudyApp instance;

    public static StudyApp instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.exit(0);
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
