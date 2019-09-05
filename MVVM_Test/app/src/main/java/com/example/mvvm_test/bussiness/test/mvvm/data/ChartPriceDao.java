package com.example.mvvm_test.bussiness.test.mvvm.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mvvm_test.bussiness.test.mvvm.model.PriceItem;

import java.util.List;

/**
 * Time:2019/8/31
 * Author: han1254
 */

@Dao
public interface ChartPriceDao {

    @Insert()
    void savePriceData(List<PriceItem> items);

    @Query("SELECT *FROM price_item WHERE item_time BETWEEN :time1 AND :time2")
    List<PriceItem> getPiecePrice(String time1,String time2);


    @Delete
    void deletePriceData(PriceItem item);

    @Update
    int updatePriceData(List<PriceItem> list);



}
