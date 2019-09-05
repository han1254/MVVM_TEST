package com.example.mvvm_test.bussiness.test.mvvm.model;

import java.lang.reflect.Proxy;

public class PriceModel {


    /**
     * time : 8月1日
     * price : 112233333
     */

    private String time;
    private Long price;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public PriceModel(String time,Long price){
        this.price = price;
        this.time = time;
    }

    public PriceModel(){

    }
}
