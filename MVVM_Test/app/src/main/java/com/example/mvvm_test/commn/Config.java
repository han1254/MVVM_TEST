package com.example.mvvm_test.commn;

import android.Manifest;

public class Config {
    /**
     * 设置BaseURL
     */
    public static final String BASE_URL = "http://tdapi.marklux.cn/";
    /**
     * 设置网络请求连接超时时间。单位秒
     */
    public static final int CONNECT_TIME_OUT = 15;
    /**
     * 返回正确码
     */
    public static final int[] NET_CORRECT_CODE = {0};

    public static final String CALENDAR = "calendar";

    //today FileProvider
    public static String FILE_PROVIDER = "com.today.android.fileprovider";
    /**
     * 图片&视频选择请求码
     **/
    public static int REQUEST_CODE_CHOOSE = 100;

    /**
     * 权限
     */
    public static String[] REQUEST_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
}

