package com.study.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.study.StudyApp;

/**
 * Created by hugo on 15/10/6.
 */
public class SharedPreferenceUtil {

    public static final String USER_NICKNAME = "USER_NICKNAME";
    public static final String USER_HEAD = "USER_HEAD";
    public static final String PREFERENCES_TYPE = "PREFERENCES_TYPE";
    public static final String IMEI = "IMEI";

    public static void setImei(String value) {
        getPreference().edit().putString(IMEI, value).commit();
    }

    public static String getImei() {
        String value = getPreference().getString(IMEI,"");
        return value;
    }

    public static void setUserNickname(String value) {
        getPreference().edit().putString(USER_NICKNAME, value).commit();
    }

    public static String getUserNickname() {
        String value = getPreference().getString(USER_NICKNAME,"");
        return value;
    }

    public static void setUserHead(String value) {
        getPreference().edit().putString(USER_HEAD, value).commit();
    }

    public static String getUserHead() {
        String value = getPreference().getString(USER_HEAD,"");
        return value;
    }

    public static void setPreferencesType(int value) {
        getPreference().edit().putInt(PREFERENCES_TYPE, value).commit();
    }

    public static int getPreferencesType() {
        int value = getPreference().getInt(PREFERENCES_TYPE,0);
        return value;
    }


    private static SharedPreferences getPreference() {
        return PreferenceManager.getDefaultSharedPreferences(StudyApp.instance());
    }

}
