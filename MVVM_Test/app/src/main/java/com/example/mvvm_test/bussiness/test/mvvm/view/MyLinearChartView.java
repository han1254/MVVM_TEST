package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.model.PriceModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MyLinearChartView extends LinearLayout {

    private Context context;
    private View view;
    private LineChart chart;

    private LineData mData;
    private List<ILineDataSet> mSets;
    private List<String> mXTable;

    public MyLinearChartView(Context contex){
        super(contex);


//        chart.setBackgroundColor(contex.getResources().getColor(R.color.white));
//        chart.setDrawGridBackground(true);
//        chart.setGridBackgroundColor(contex.getResources().getColor(R.color.pink));

    }


    public MyLinearChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.item_linear_chart,this);
        chart = view.findViewById(R.id.item_linear_chart);
    }

    public MyLinearChartView setXIsVisible(boolean isVisible){
        chart.getXAxis().setDrawAxisLine(isVisible);
        return this;
    }

    public MyLinearChartView setGridIsVisible(boolean isVisible){
        chart.getXAxis().setDrawGridLines(isVisible);
        return this;
    }

    public MyLinearChartView setLegendIsVisible(boolean isVisible){
        chart.getLegend().setEnabled(isVisible);
        return this;
    }

    public MyLinearChartView setDescription(String content){
        chart.setDescription(content);
        return this;
    }

    public MyLinearChartView setYIsVisible(boolean isLeftVisible, boolean isRightVisible){
        chart.getAxisLeft().setEnabled(isLeftVisible);
        chart.getAxisRight().setEnabled(isRightVisible);
        return this;
    }


    public MyLinearChartView setXPosition(XAxis.XAxisPosition position){
        chart.getXAxis().setPosition(position);
        return this;
    }

    /**
     * 设置chartview的background
     * @param color
     * @return
     */

    public MyLinearChartView setBackground(int color){
        chart.setBackgroundColor(context.getResources().getColor(color));
        return this;
    }


    public MyLinearChartView setGridBg(int color){
        chart.setGridBackgroundColor(context.getResources().getColor(color));
        return this;
    }




    public LineChart getChart(){
        return chart;
    }



    public void setData(LineData data){
        chart.setData(data);
    }

    public void setData(List<String > xTable,List<ILineDataSet> sets){
        mXTable = xTable;
        mSets = sets;
        LineData data = new LineData(xTable,mSets);
        setData(data);
    }

    public void setData(List<String> xTable){
        LineData data = new LineData(xTable,mSets);
        chart.setData(data);
    }


    public void addData(LineDataSet set){
        mSets.add(set);
    }

    public void addDatas(List<ILineDataSet> sets){
        mSets.addAll(sets);
    }

    public View getView(){
        return view;
    }




    public void toShowGap(@ColorRes int bg_color, @ColorRes int cover_color,List<ILineDataSet> dataSets,float start, float end,int n){

        chart.setBackgroundColor(context.getResources().getColor(R.color.white));
        chart.setDrawGridBackground(true);
        chart.setGridBackgroundColor(context.getResources().getColor(bg_color));

        List<Float> y1 = new ArrayList<>();
        List<Float> y2 = new ArrayList<>();
        for(int i = 0; i < n; i++){
            y1.add(start);
            y2.add(end);
        }
        MyLineDataSet set1 = new MyLineDataSet(context,y1,"");
        MyLineDataSet set2 = new MyLineDataSet(context,y2,"");
        set1.IsCircleVisible(false)
                .setLineWidth(0)
                .setIsFilled(true,cover_color)
                .setDrawableValue(true)
                .setAlpha(255);


        set2.IsCircleVisible(false)
                .setIsFilled(true,cover_color)
                .setDrawableValue(true)
                .setLineWidth(0)
                .setAlpha(255);

        if (y1.get(0)> y2.get(0)){
            set1.getmSet().setFillFormatter(new FillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMaximum();
                }
            });

            set2.getmSet().setFillFormatter(new FillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
        }else {
            set2.getmSet().setFillFormatter(new FillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMaximum();
                }
            });

            set1.getmSet().setFillFormatter(new FillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
        }

        dataSets.add(set1.getmSet());
        dataSets.add(set2.getmSet());
        mSets = dataSets;

    }

}
