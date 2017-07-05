package com.example.corpit.testnfc.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by corpit on 29/6/2017.
 */

public class CommonFunction {
    public static MyDatabaseHelper createOrUseDataBase(Context context) {
        // 创建DatabaseHelper对象
        // 只执行这句话是不会创建或打开连接的
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        // 调用getReadableDatabase()或getWritableDatabase()才算真正创建或打开数据库
//        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        return dbHelper;
    }

    public static void showToast(Context context, String text, boolean isLongTime) {
        // Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        Toast toast = new Toast(context);
        if (isLongTime) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        // toast.setGravity(Gravity.CENTER, 0, 100);// Toast的位置
        RelativeLayout rl = new RelativeLayout(context);
        rl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView textView = new TextView(context);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(16);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setBackgroundColor(Color.argb(200, 0, 0, 0)); // 背景透明度
        textView.setGravity(Gravity.CENTER);
        rl.setPadding(40, 0, 40, 0);
        rl.addView(textView);
        toast.setView(rl);
        toast.show();
    }

    public static void showToast(Context context, int id) {
        String text = context.getResources().getString(id);
        showToast(context, text, false);
    }

    public static void showToast(Context context, int id, boolean isLongTime) {
        String text = context.getResources().getString(id);
        showToast(context, text, isLongTime);
    }

    public static Typeface getTypeFace(Context context, String type) {
        if ("bold".equals(type)) {
            return Typeface.createFromAsset(context.getAssets(), "Belleza-Regular.ttf");
        } else if ("normal".equals(type)) {
            return Typeface.createFromAsset(context.getAssets(), "Belleza-Regular.ttf");
        } else if ("italic".equals(type)) {
            return Typeface.createFromAsset(context.getAssets(), "Belleza-Regular.ttf");
        }
        return Typeface.createFromAsset(context.getAssets(), "Belleza-Regular.ttf");
    }

    public static Typeface getTypeFace(Context context) {
        return getTypeFace(context, "normal");
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime(long time) {
        String ClickTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ClickTime = sdf.format(new Date(time));
        return ClickTime;
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     *
     * @param time
     * @return
     */
    public static String convert2String(long time) {
        if (time > 0l) {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime() {
        String ClickTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ClickTime = sdf.format(new Date());
        return ClickTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static long getTimeDistance(String date1, String date2) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begin = dfs.parse(date1);
            Date end = dfs.parse(date2);
            if (end.after(begin)) {
                long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
                return between;
            } else {
                return -1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @SuppressLint("SimpleDateFormat")
    public static boolean DateCompare() {
        try {
            // 设定时间的模板
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 得到指定模范的时间
            Date d1;

            d1 = sdf.parse("2015-01-31 00:00:00");

            Date d2 = new Date();
            // 比较
            if (d1.getTime() > d2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
