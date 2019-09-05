package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.model.PriceModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.util.ArrayList;
import java.util.List;

public class ChartViewTest extends AppCompatActivity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view_test);
        iniData();
        initView();
    }

    private void iniData(){
        chart = (LineChart)findViewById(R.id.price_linear_chart);
    }

    private void initView(){

        initChart();
    }

    private void initChart(){


        chart.getLegend().setEnabled(false);
        chart.setDescription(null);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);





        XAxis xAxis = chart.getXAxis();


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);



        YAxis yAxis = chart.getAxisLeft();
        yAxis.setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);

        chart.setData(getData());
    }

    private LineData getData(){

        List<PriceModel> list1 = new ArrayList<>();
        List<PriceModel> list2 = new ArrayList<>();
        List<PriceModel> list3 = new ArrayList<>();


        List<Entry> entries1 = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();
        List<Entry> entries3 = new ArrayList<>();



        for(int i = 0; i <= 6; i++){
            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(Double.valueOf(Math.random()*30000).longValue());
            priceModel.setTime("1."+String.valueOf(i));
            Entry entry = new Entry((float)Math.random()*30000,i);
            entries1.add(entry);
        }

        for(int i = 0; i <= 6; i++){
            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(Double.valueOf(Math.random()*30000).longValue());
            priceModel.setTime("1."+String.valueOf(i));
            Entry entry = new Entry((float)Math.random()*30000,i);
            entries2.add(entry);
        }

        for(int i = 0; i <= 6; i++){
            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(Double.valueOf(Math.random()*30000).longValue());
            priceModel.setTime("1."+String.valueOf(i));
            Entry entry = new Entry((float)Math.random()*30000,i);
            entries3.add(entry);
        }


        LineDataSet set1 = new LineDataSet(entries1,"");
        set1.setColor(getResources().getColor(R.color.red));
        set1.setDrawCubic(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        LineData

        LineDataSet set2 = new LineDataSet(entries2,"");
        set2.setColor(getResources().getColor(R.color.orange));
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineDataSet set3 = new LineDataSet(entries3,"");
        set3.setColor(getResources().getColor(R.color.yellow));
        set3.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        List<ILineDataSet> listDataset = new ArrayList<>();
        listDataset.add(set1);
        listDataset.add(set2);
        listDataset.add(set3);

        List<String> xtable = new ArrayList<>();
        xtable.add("Mon");
        xtable.add("Tus");
        xtable.add("Wen");
        xtable.add("Thu");
        xtable.add("Fri");
        xtable.add("Sat");
        xtable.add("Sun");


        LineData data = new LineData(xtable,listDataset);

        return  data;

    }




}
