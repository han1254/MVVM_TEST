package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.BaseAdapter;

import com.example.mvvm_test.R;
import com.example.mvvm_test.base.baseveiwholder.BaseViewHolder;
import com.example.mvvm_test.base.paging.PagingAdapter;
import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;

import java.util.Calendar;

public class TestPagedAdapter extends PagingAdapter<CalendarModel> {

    public TestPagedAdapter(Context context, LifecycleOwner owner, int layoutId) {
        super(sDiffCallback, context, owner, layoutId);
    }

    @Override
    public void bindView(BaseViewHolder holder, CalendarModel data) {
        holder.setText(R.id.item_name,data.getCalendarName())
                .setText(R.id.item_id,String.valueOf(data.getCalendarId()));
    }

    static DiffUtil.ItemCallback<CalendarModel> sDiffCallback = new DiffUtil.ItemCallback<CalendarModel>() {


        @Override
        public boolean areItemsTheSame(@NonNull CalendarModel calendarModel, @NonNull CalendarModel t1) {
            return calendarModel.getCalendarName().equals(t1.getCalendarName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CalendarModel calendarModel, @NonNull CalendarModel t1) {
            return String.valueOf(calendarModel.getCalendarId()).equals(String.valueOf(t1.getCalendarId()));
        }
    };
}
