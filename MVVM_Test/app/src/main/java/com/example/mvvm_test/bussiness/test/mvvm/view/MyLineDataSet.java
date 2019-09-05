package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.content.Context;
import android.support.annotation.ColorRes;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MyLineDataSet {
   private List<LineDataSet> lineDataSets = new ArrayList<>();

   private LineDataSet mSet;
   Context context;

   public MyLineDataSet(Context context,List<Float> list,String label){
       this.context = context;
       List<Entry> entries = new ArrayList<>();
       int i = 0;
       for(Float n : list){
           Entry entry = new Entry(n,i);
           entries.add(entry);
           i++;
       }

       LineDataSet set = new LineDataSet(entries,label);
       this.mSet = new LineDataSet(entries,label);
   }

   public void setDataSet(List<Float> list,String label){


       List<Entry> entries = new ArrayList<>();
       int i = 0;
       for(Float n : list){
           Entry entry = new Entry(n,i);
           entries.add(entry);
       }

       LineDataSet set = new LineDataSet(entries,label);
       mSet = new LineDataSet(entries,label);

   }

   public MyLineDataSet setColor(@ColorRes int color){
       mSet.setColor(context.getResources().getColor(color));
       return this;
   }

   public MyLineDataSet setMode(LineDataSet.Mode mode){
       mSet.setMode(mode);

       return this;
   }

   public MyLineDataSet IsCircleVisible(boolean isVisible){
       mSet.setDrawCircles(isVisible);
       return this;
   }

   public MyLineDataSet setCircleColor(@ColorRes int res){
       mSet.setCircleColor(context.getResources().getColor(res));
       return this;
   }

   public MyLineDataSet setLineWidth(float width){
       mSet.setLineWidth(width);
       return this;
   }

   public MyLineDataSet setIsFilled(boolean isFilled,@ColorRes int color){
       mSet.setDrawFilled(isFilled);
       mSet.setFillColor(context.getResources().getColor(color));
       return this;
   }

  public MyLineDataSet setAlpha(int alpha){
       mSet.setFillAlpha(alpha);
       return this;
  }


  public MyLineDataSet setDrawableValue(boolean isValue){
       mSet.setDrawValues(isValue);
       return this;
  }


   public LineDataSet getmSet(){
       return mSet;
   }




}
