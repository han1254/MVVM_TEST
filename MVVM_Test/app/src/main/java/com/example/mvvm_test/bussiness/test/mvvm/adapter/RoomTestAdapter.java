package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.example.mvvm_test.R;
import com.example.mvvm_test.base.baseveiwholder.BaseViewHolder;
import com.example.mvvm_test.base.paging.PagingAdapter;
import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;

public class RoomTestAdapter extends PagingAdapter<Person> {
    public RoomTestAdapter(Context context, LifecycleOwner owner, int layoutId) {
        super(sDiffCallback, context, owner, layoutId);
    }

    @Override
    public void bindView(BaseViewHolder holder, Person data) {
        holder.setText(R.id.item_name,data.getName())
                .setText(R.id.item_age,String.valueOf(data.getAge()))
                .setText(R.id.item_id,String.valueOf(data.getId()));
    }

    static DiffUtil.ItemCallback<Person> sDiffCallback = new DiffUtil.ItemCallback<Person>() {

        @Override
        public boolean areItemsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person.id == t1.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person person, @NonNull Person t1) {
            return TextUtils.equals(person.getName(),t1.getName());
        }
    };

}
