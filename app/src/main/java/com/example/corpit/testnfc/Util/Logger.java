package com.example.corpit.testnfc.Util;

import android.util.Log;

/**
 * Created by corpit on 3/7/2017.
 */

public abstract class Logger {
    private static boolean DEBUGGABLE = true;

    private static String TAG = Logger.class.getName();


    public static void warn(String TAG, String message) {
        if (isDebugEnable()) {
            Log.w(TAG, message);
        }
    }

    public static void error(String TAG, String message) {
        if (isDebugEnable()) {
            Log.e(TAG, message);
        }
    }

    public static void debug(String TAG, String message) {
        if (isDebugEnable()) {
            Log.d(TAG, message);
        }
    }

    public static void info(String TAG, String message) {
        if (isDebugEnable()) {
            Log.i(TAG, message);
        }
    }

    public static void ver(String TAG, String message) {
        if (isDebugEnable()) {
            Log.v(TAG, message);
        }
    }

    private static boolean isDebugEnable() {

        return DEBUGGABLE;

    }
}
