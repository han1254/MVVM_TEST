package com.example.android.utils;

import android.content.Context;

/**
 * @author YangZhaoxin.
 * @since 2019/8/29 19:37.
 * email yangzhaoxin@hrsoft.net.
 */

public class ScreenUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context   context
     * @param dpValue   要转换的dp值
     * @return
     */
    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
