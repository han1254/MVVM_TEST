package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvvm_test.R;

public class SelectorPickerActivity extends AppCompatActivity {

    private Button easy_photoBtn;
    private Button zhihuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_picker);
        initData();
        initView();

    }


    private void initData(){

    }

    private void initView(){
        easy_photoBtn = (Button)findViewById(R.id.easy_photo);
        zhihuBtn = (Button)findViewById(R.id.btn_zhihu);

        easy_photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectorPickerActivity.this, EasyPhotoActivity.class);
                startActivity(intent);
            }
        });
    }
}
