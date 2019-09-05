package com.example.mvvm_test.bussiness.test.mvvm.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.mvvm_test.bussiness.test.mvvm.model.PriceItem;
import com.example.mvvm_test.runtime.AppRuntime;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2019/8/31
 * Author: han1254
 */
public class ChartPriceRepository {

    private ChartPriceDataBase dataBase;

    private ChartPriceRepository(){
        dataBase = Room.databaseBuilder(AppRuntime.getAppContext(),ChartPriceDataBase.class,"chart_price.db").build();
    }

    static class ChartPriceRepositoryHolder{
        static ChartPriceRepository instance = new ChartPriceRepository();
    }

    public static ChartPriceRepository getInstance(){
        return ChartPriceRepositoryHolder.instance;
    }

    public List<PriceItem> getPiecePrice(String startTime, String endTime){

       return dataBase.getChartPriceDao().getPiecePrice(startTime,endTime);

    }

    public void saveCharPriceData(List<PriceItem> list){
        dataBase.getChartPriceDao().savePriceData(list);
    }

    public void getData(){
        PriceItem item1 = new PriceItem("2019-07-01","1000");
        PriceItem item2 = new PriceItem("2019-07-02","2000");
        PriceItem item3 = new PriceItem("2019-07-03","3000");
        PriceItem item4 = new PriceItem("2019-07-04","4000");
        PriceItem item5 = new PriceItem("2019-07-05","4500");
        PriceItem item6 = new PriceItem("2019-07-06","3900");
        PriceItem item7 = new PriceItem("2019-07-07","4100");
        PriceItem item8 = new PriceItem("2019-07-08","1600");
        PriceItem item9 = new PriceItem("2019-07-09","3800");
        PriceItem item10 = new PriceItem("2019-07-10","2900");
        PriceItem item11 = new PriceItem("2019-07-11","1900");
        PriceItem item12 = new PriceItem("2019-07-12","3800");
        PriceItem item13 = new PriceItem("2019-07-13","2600");
        PriceItem item14 = new PriceItem("2019-07-14","2800");

        List<PriceItem> chartPriceData = new ArrayList<>();
        chartPriceData.add(item1);
        chartPriceData.add(item2);
        chartPriceData.add(item3);
        chartPriceData.add(item4);
        chartPriceData.add(item5);
        chartPriceData.add(item6);
        chartPriceData.add(item7);
        chartPriceData.add(item8);
        chartPriceData.add(item9);
        chartPriceData.add(item10);
        chartPriceData.add(item11);
        chartPriceData.add(item12);
        chartPriceData.add(item13);
        chartPriceData.add(item14);

      new Thread(new Runnable() {
           @Override
           public void run() {
               dataBase.getChartPriceDao().updatePriceData(chartPriceData);
           }
       }).start();

    }

    public ChartPriceDataBase getDataBase(){
        return dataBase;
    }


}
