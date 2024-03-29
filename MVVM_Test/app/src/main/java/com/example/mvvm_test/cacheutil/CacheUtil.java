package com.example.mvvm_test.cacheutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.mvvm_test.App;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CacheUtil {


    // 保存在手机里的文件名字
    private static final String FILE_NAME = "share_data";

    /**
     * 获取sp实例
     * @return
     */
    public static SharedPreferences getSP() {
        return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        SharedPreferences sp = getSP();
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Serializable) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                //把对象写到流里
                oos.writeObject(value);
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        editor.apply();
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        SharedPreferences sp = getSP();
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = getSP();
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

}
