package com.example.corpit.testnfc.Data;

/**
 * Created by corpit on 29/6/2017.
 */

public class DatabaseConstants {
    //数据库版本号
    public static final int DATABASE_VERSION = 4;
    //数据库名
    public static String DATABASE_NAME = "NFCDatabase.db";
    //表名
    public static String TABLE_CUSTOMER_NAME = "Customer";
    public static String TABLE_CHECK_IN_NAME = "CheckIn";
    public static String TABLE_DEVICE_NAME = "Device";
    public static String TABLE_CHECK_IN_TYPE_NAME = "CheckInType";
    public static String TABLE_CHECK_IN_POINT_NAME = "CheckInPoint";
    public static String TABLE_PHOTO_NAME = "Photo";
    public static String TABLE_PHOTOGRAPHER_NAME = "Photographer";

    public static String CREATE_TABLE_CUSTOMER = "CREATE TABLE `" + TABLE_CUSTOMER_NAME + "` ("
            + "`id`\tTEXT PRIMARY KEY,"
            + "`name`\tTEXT,"
            + "`NFCNumber`\tTEXT,"
            + "`foodPreference`\tTEXT,"
            + "`galaDinnerTime`\tTEXT,"
            + "`circuitTime`\tTEXT,"
            + "`jobTitle`\tTEXT,"
            + "`isCheckIn`\tTEXT,"
            + "`isGalaDinnerCheckIn`\tTEXT,"
            + "`quizResult`\tTEXT);";

    public static String CREATE_TABLE_CUSTOMER_CHECK_IN_INFO = "CREATE TABLE `" + TABLE_CHECK_IN_NAME + "` ("
            + "`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + "`checkInTime`\tTEXT,"
            + "`checkInDeviceId`\tINTEGER,"
            + "`checkInTypeId`\tINTEGER,"
            + "`checkInPointId`\tINTEGER,"
            + "`NFCNumber`\tTEXT\n" +
            ");";

    public static String CREATE_TABLE_DEVICE = "CREATE TABLE `"+ TABLE_DEVICE_NAME + "` (\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`deviceName`\tTEXT,\n" +
            "\tPRIMARY KEY(`id`)\n" +
            ");";

    public static String CREATE_TABLE_CHECK_IN_TYPE = "CREATE TABLE `"+ TABLE_CHECK_IN_TYPE_NAME  + "` (\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`typeName`\tTEXT,\n" +
            "\tPRIMARY KEY(`id`)\n" +
            ");";

    public static String CREATE_TABLE_CHECK_IN_POINT = "CREATE TABLE `"+ TABLE_CHECK_IN_POINT_NAME  + "` (\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`pointName`\tTEXT,\n" +
            "\t`checkInTypeId`\tTEXT,\n" +
            "\tPRIMARY KEY(`id`)\n" +
            ");";

    public static String CREATE_PHOTO = "CREATE TABLE `"+ TABLE_PHOTO_NAME  + "` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`photoName`\tTEXT,\n" +
            "\t`deviceId`\tINTEGER,\n" +
            "\t`photographerId`\tINTEGER,\n" +
            "\t`contentCustomer`\tTEXT\n" +
            ");";

    public static String CREATE_PHOTOGRAPHER = "CREATE TABLE `"+ TABLE_PHOTOGRAPHER_NAME  + "` (\n" +
            "\t`id`\tINTEGER,\n" +
            "\t`name`\tTEXT,\n" +
            "\tPRIMARY KEY(`id`)\n" +
            ");";
}
