package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mvvm_test.Callback;
import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.paint.activity.PaintActivity;
import com.example.mvvm_test.bussiness.test.mvvm.adapter.PagingAdapter;
import com.example.mvvm_test.bussiness.test.mvvm.adapter.RecyclerAdapter;
import com.example.mvvm_test.bussiness.test.mvvm.adapter.TestPagedAdapter;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.TestViewModel;
import com.example.mvvm_test.data.DBController;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.MyViewModel;
import com.example.mvvm_test.bussiness.test.repository.Repository;
import com.github.mikephil.charting.charts.Chart;

import java.io.Serializable;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback<LiveData<PagedList<Person>>>{

    private LiveData<PagedList<Person>> mLiveData;
    private PagingAdapter adapter;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private Context mContext;
    private Button button;
    private Button image_picker_btn;
    private Button line_chart_btn;
    private Button priceTestBtn;
    private Button btnNext;
    private Button panitTestButton;
    private List<Person> personlist = new ArrayList<>();
    private Repository repository;

    private TestPagedAdapter testPagedAdapter;
    private TestViewModel testViewModel;

    //测试ViewModel
    private MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        initView();
    }


    public void initData(){

        testPagedAdapter = new TestPagedAdapter(this,this,R.layout.item_view);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        testPagedAdapter.setViewModel(testViewModel);

        adapter = new PagingAdapter(this);
        mContext = this;
        final PagedList.Config pageConfig = new PagedList.Config.Builder()
                .setPageSize(20)
                .setPrefetchDistance(5)
                .setInitialLoadSizeHint(20)
                .build();

        Person person1 = new Person("张三",10,1);
        personlist.add(person1);
        Person person2 = new Person("李四",12,2);
        personlist.add(person2);
        Person person3 = new Person("王五",13,3);
        personlist.add(person3);
        Person person4 = new Person("赵六",16,4);
        personlist.add(person4);

//        recyclerAdapter = new RecyclerAdapter(this,personlist);



//        DBController.getInstance().savePersons(personlist);


        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);



    }


    public void setDataObserver(){

        mLiveData.observe((LifecycleOwner) this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(@Nullable PagedList<Person> people) {
                adapter.submitList(people);
            }
        });
    }

    public void initView(){

        btnNext = (Button)findViewById(R.id.btn_next);
        image_picker_btn = (Button)findViewById(R.id.main_btn_imagepicker);
        line_chart_btn = (Button)findViewById(R.id.main_btn_lineChartView);
        priceTestBtn = (Button)findViewById(R.id.main_btn_price_test);
        panitTestButton = (Button)findViewById(R.id.main_btn_paint_test);

        panitTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaintActivity.class);
                startActivity(intent);
            }
        });

        priceTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PriceTestActivity.class);
                startActivity(intent);
            }
        });

        line_chart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartViewTest.class);
                startActivity(intent);
            }
        });

        image_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectorPickerActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RoomTestActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.livedata_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Person> list = new ArrayList<>();
                Person person1 = new Person("张三",10,1);
                list.add(person1);
                Person person2 = new Person("lisi",12,2);
                list.add(person2);
                Person person3 = new Person("wagnwu",13,3);
                list.add(person3);
                Person person4 = new Person("zhaoliu",16,4);
                list.add(person4);

                DBController.getInstance().savePersons(list);



            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setAdapter(testPagedAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }




    @Override
    public void onCallback(LiveData<PagedList<Person>> pagedListLiveData) {
        pagedListLiveData.observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(@Nullable PagedList<Person> people) {
                adapter.submitList(people);
            }
        });
    }
}
