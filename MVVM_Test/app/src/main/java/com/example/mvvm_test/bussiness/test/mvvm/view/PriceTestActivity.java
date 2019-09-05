package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.adapter.PriceTestAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceTestActivity extends AppCompatActivity {

    @BindView(R.id.price_uni_recycler)
    RecyclerView priceUniRecycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private PriceTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_test);
        ButterKnife.bind(this);

        initData();
        initValue();
        initView();

    }

    private void initData() {
        adapter = new PriceTestAdapter(this, null);
    }

    private void initValue() {

    }

    private void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 19:
                        return 4;
                    default:
                        return 1;
                }
            }
        });

        priceUniRecycler.setAdapter(adapter);
        priceUniRecycler.setLayoutManager(gridLayoutManager);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
            }
        });
    }
}
