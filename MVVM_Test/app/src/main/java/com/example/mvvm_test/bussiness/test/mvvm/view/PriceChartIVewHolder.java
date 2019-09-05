package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.data.ChartPriceRepository;
import com.example.mvvm_test.bussiness.test.mvvm.model.PriceItem;
import com.example.mvvm_test.bussiness.test.mvvm.model.PriceModel;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceChartIVewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.price_linear_chart_content)
    LinearLayout priceLinearChartContent;

    @BindView(R.id.item_price_uni_mylinechart)
    MyLinearChartView chartView;

    @BindView(R.id.up_rank)
    TextView textView;


    private List<PriceItem> testList;

    public PriceChartIVewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(List<PriceModel> list, int position, Context context) {
        ChartPriceRepository.getInstance().getData();







        List<PriceModel> list1 = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(Double.valueOf(Math.random() * 30000).longValue());
            priceModel.setTime("1." + String.valueOf(i));
            list1.add(priceModel);
        }

        List<Float> y = new ArrayList<>();
        List<Float> y1 = new ArrayList<>();
        List<Float> y2 = new ArrayList<>();

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

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(new Runnable() {
            @Override
            public void run() {
                testList = ChartPriceRepository.getInstance().getPiecePrice("2019-07-01","2019-07-07");
//                textView.setText(testList.get(0).getTime());

                for (PriceItem p : testList) {
                    y.add((float) Integer.valueOf(p.getPrice()));

                }

                List<ILineDataSet> dataSets = new ArrayList<>();

                MyLineDataSet set = new MyLineDataSet(context, y, "");
                set.IsCircleVisible(false)
                        .setColor(R.color.red)
                        .setDrawableValue(false)
                        .setMode(LineDataSet.Mode.CUBIC_BEZIER);

                dataSets.add(set.getmSet());


                //设置x坐标
                List<String> xtable = new ArrayList<>();
                xtable.add("Mon");
                xtable.add("Tus");
                xtable.add("Wen");
                xtable.add("Thu");
                xtable.add("Fri");
                xtable.add("Sat");
                xtable.add("Sun");

//         LineData data = new LineData(xtable, dataSets);

                chartView.setXIsVisible(false)
                        .setYIsVisible(false, false)
                        .setLegendIsVisible(false)
                        .setGridIsVisible(false)
                        .setDescription(null)
                        .setXPosition(XAxis.XAxisPosition.BOTTOM);
                chartView.toShowGap(R.color.pink, R.color.white, dataSets, y.get(0), y.get(y.size() - 1), list1.size());
                chartView.setData(xtable);
            }
        });



//
//        //设置y值
//        for (PriceModel p : list1) {
//            y.add((float) p.getPrice());
////             y1.add((float)list1.get(0).getPrice());
////             y2.add((float)list1.get(list1.size()-1).getPrice());
//        }


//
//        priceLinearChartContent.addView(chartView.getView());
    }
}
