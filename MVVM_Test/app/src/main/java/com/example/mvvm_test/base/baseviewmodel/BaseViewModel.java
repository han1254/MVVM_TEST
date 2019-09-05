package com.example.mvvm_test.base.baseviewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.mvvm_test.base.paging.PagingDataSource;
import com.example.mvvm_test.base.paging.PagingFactory;

public abstract class BaseViewModel<T> extends ViewModel {


    private LiveData<PagedList<T>> mDataList;
    private PagingFactory<T> factory;

    public BaseViewModel() {
        factory = new PagingFactory<T>(getDataSource());
        mDataList = getList();
    }

    public LiveData<PagedList<T>> getList(){
        mDataList = new LivePagedListBuilder<>(factory,getConfig()).build();
        return mDataList;
    }

    private PagedList.Config getConfig() {
        return new PagedList.Config.Builder()
                //配置分页加载的数量
                .setPageSize(20)
                //配置是否启动PlaceHolders
                .setEnablePlaceholders(false)
                //初始化加载的数量
                .setInitialLoadSizeHint(20)
                .build();
    }

    public abstract PagingDataSource<T> getDataSource();
}
