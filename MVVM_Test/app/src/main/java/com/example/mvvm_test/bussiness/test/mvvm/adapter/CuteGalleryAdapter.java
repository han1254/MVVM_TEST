package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mvvm_test.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/9/3
 * Author: han1254
 */
public class CuteGalleryAdapter extends RecyclerView.Adapter<CuteGalleryAdapter.VH> {


    private Context context;
    private List<Integer> mDatas;

    public CuteGalleryAdapter(Context context, List<Integer> mDatas){
        this.context = context;
        this.mDatas = mDatas;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.galleryitem,viewGroup,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.ivBig.setImageDrawable(context.getResources().getDrawable(mDatas.get(i)));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class VH extends RecyclerView.ViewHolder {

//        View view = R.layout.galleryitem;
        @BindView(R.id.iv_big)
        ImageView ivBig;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
