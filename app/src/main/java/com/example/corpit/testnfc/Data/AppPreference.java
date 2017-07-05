package com.example.corpit.testnfc.Data;

import android.content.Context;

/**
 * Created by corpit on 29/6/2017.
 */

public class AppPreference {
    public static final String APP_PREF = "app_prefs";

    public static final String DEVICE = "device";
    public static final String FEATURE = "feature";
    public static final String CHECK_IN_TYPE = "checkInType";
    public static final String CHECK_IN_POINT = "checkInPoint";
    public static final String PHOTOGRAPHER = "photographer";
    // TO Store String
    public static void updatePref(Context context, String pref, String value) {
        context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit().putString(pref, value).commit();
    }

    // TO Stroe Integer
    public static void updatePref(Context context, String pref, int value) {
        context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit().putInt(pref, value).commit();
    }

    // TO Store Boolean
    public static void updatePref(Context context, String pref, boolean value) {
        context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit().putBoolean(pref, value).commit();
    }

    // TO Get Integer
    public static int getPrefInt(Context context, String pref) {
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).getInt(pref, 0);
    }

    // TO Get string
    public static String getPref(Context context, String pref) {
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).getString(pref, null);
    }

    // TO Get boolean
    public static boolean getPrefBoolean(Context context, String pref) {
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).getBoolean(pref, false);
    }

    // TO  Clear The SharedPreference
    public static void clearUserPreference(Context context) {
        context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE).edit().clear().commit();
    }
}
