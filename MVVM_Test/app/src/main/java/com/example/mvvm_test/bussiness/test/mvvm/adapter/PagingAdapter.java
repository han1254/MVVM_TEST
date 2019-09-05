package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.MyViewModel;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.RoomTestViewModel;

public class PagingAdapter extends PagedListAdapter<Person,PagingAdapter.PersonViewHolder> {

    private LifecycleOwner owner;

    private RoomTestViewModel mViewModel;

    private final Observer<PagedList<Person>> observer = new Observer<PagedList<Person>>() {
        @Override
        public void onChanged(@Nullable PagedList<Person> people) {
            submitList(people);
        }
    };

    public PagingAdapter(LifecycleOwner owner) {
        super(sDiffCallback);
        this.owner = owner;
    }

    @NonNull
    @Override
    public PagingAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PagingAdapter.PersonViewHolder personViewHolder, int i) {
        personViewHolder.mTvId.setText(String.valueOf(getItem(i).getId()));
        personViewHolder.mTvName.setText(getItem(i).getName());
        personViewHolder.mTvage.setText(String.valueOf(getItem(i).getAge()));
    }


    class PersonViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvName;
        private TextView mTvage;
        private TextView mTvId;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.item_name);
            mTvage = itemView.findViewById(R.id.item_age);
            mTvId = itemView.findViewById(R.id.item_id);
        }
    }


    static DiffUtil.ItemCallback<Person> sDiffCallback = new DiffUtil.ItemCallback<Person>() {

        @Override
        public boolean areItemsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person == t1;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person == t1;
        }
    };


    public void setViewModel(RoomTestViewModel viewModel){
        if(mViewModel != null){
            mViewModel.getData().removeObserver(observer);
        }
        mViewModel = viewModel;
        mViewModel.getData().observe(owner,observer);
    }


}
