package com.example.mvvm_test.bussiness.test.mvvm.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.mvvm_test.bussiness.test.mvvm.model.PriceItem;

/**
 * Time:2019/8/31
 * Author: han1254
 */

@Database(entities = {PriceItem.class}, version = 1,exportSchema = false)
public abstract class ChartPriceDataBase extends RoomDatabase {
   public abstract ChartPriceDao getChartPriceDao();
}
