package com.example.mvvm_test.base.paging;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_test.base.baseveiwholder.BaseViewHolder;
import com.example.mvvm_test.base.baseviewmodel.BaseViewModel;

public  abstract class PagingAdapter<T> extends PagedListAdapter<T, BaseViewHolder>{


    private Context context;
    private int layoutId;
    private BaseViewModel<T> mViewModel;
    private LifecycleOwner owner;
    private final Observer<PagedList<T>> observer = this::submitList;

    protected PagingAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, Context context,LifecycleOwner owner,int layoutId) {
        super(diffCallback);
        this.context = context;
        this.layoutId = layoutId;
        this.owner = owner;
    }


    public void setViewModel(BaseViewModel<T> viewModel){
        if(mViewModel != null){
            mViewModel.getList().removeObserver(observer);
        }

        mViewModel = viewModel;
        mViewModel.getList().observe(owner,observer);
    }




    public abstract void bindView(BaseViewHolder holder, T data);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(layoutId,viewGroup,false);
        return new BaseViewHolder(context,v);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        T data = getItem(i);
        bindView(baseViewHolder,data);
    }
}
