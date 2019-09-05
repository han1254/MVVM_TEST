package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.content.Context;
import android.provider.ContactsContract;

import com.example.mvvm_test.R;
import com.example.mvvm_test.base.baseveiwholder.BaseRecyclerViewAdapter;
import com.example.mvvm_test.base.baseveiwholder.BaseViewHolder;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.List;

public class EasyPhotoAdapter extends BaseRecyclerViewAdapter<Photo> {

    public EasyPhotoAdapter(List<Photo> photos, Context context, int itemLayoutId) {
        super(photos, context, itemLayoutId);

    }

    @Override
    protected void bindView(BaseViewHolder viewHolder, Photo item) {
        viewHolder.setImgUrl(R.id.image_toshow,item.cropPath == null || item.cropPath.isEmpty() ?item.path : item.cropPath);

    }
}
