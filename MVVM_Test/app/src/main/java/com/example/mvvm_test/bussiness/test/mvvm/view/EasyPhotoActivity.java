package com.example.mvvm_test.bussiness.test.mvvm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.adapter.EasyPhotoAdapter;
import com.example.mvvm_test.util.GlideEngine;
import com.example.mvvm_test.util.LubanCompressEngine;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.constant.Type;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class EasyPhotoActivity extends AppCompatActivity {


    private Button btnAdd;
    private RecyclerView imgRecyclerView;

    private EasyPhotoAdapter easyPhotoAdapter;
    private List<Photo> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_photo);
        initData();
        initView();
    }


    private void initData(){

        btnAdd = (Button)findViewById(R.id.easy_photo_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPhotos.createAlbum(EasyPhotoActivity.this,true,GlideEngine.getInstance())
                        .isCrop(true)
                        .setFreeStyleCropEnabled(true)
                        .setOriginalMenu(false,true,null)
                        .filter(Type.all())
                        .setGif(true)
                        .setSelectMutualExclusion(true) //选择结果互斥（不能同时选择图片或视频）
                        .isCompress(true)
                        .setCompressEngine(LubanCompressEngine.getInstance())
                        .start(selectCallback);
            }
        });

    }

    private SelectCallback selectCallback = new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            mPhotos.addAll(photos);
            easyPhotoAdapter.notifyDataSetChanged();
        }
    };

    private void initView(){

        imgRecyclerView = (RecyclerView)findViewById(R.id.easy_photo_recycler);
        easyPhotoAdapter = new EasyPhotoAdapter(mPhotos,this,R.layout.item_selector_pics);
        imgRecyclerView.setAdapter(easyPhotoAdapter);
        imgRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

    }
}
