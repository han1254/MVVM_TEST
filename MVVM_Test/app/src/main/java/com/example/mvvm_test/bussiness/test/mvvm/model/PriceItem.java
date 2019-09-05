package com.example.mvvm_test.bussiness.test.mvvm.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.mvvm_test.base.model.BaseModel;

/**
 * Time:2019/8/31
 * Author: han1254
 */
@Entity(tableName = "price_item")
public class PriceItem extends BaseModel {
    /**
     * time : 08-17
     * price : 3600
     */

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item_time")
    private String time;

    @ColumnInfo(name = "item_price")
    private String price;


    public PriceItem(String time, String price){
        this.time = time;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    @NonNull
//    @PrimaryKey
//    @ColumnInfo(name = "time")
//    public String time;
//
//    @ColumnInfo(name = "price")
//    public int price;






}
