package com.example.mvvm_test.base.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class PagingFactory<T> extends DataSource.Factory<Integer,T> {


    private MutableLiveData<PagingDataSource<T>> mutableLiveData = new MutableLiveData<>();

    public PagingFactory(PagingDataSource<T> dataSource){
        mutableLiveData.setValue(dataSource);
    }

    @Override
    public DataSource<Integer, T> create() {
        return mutableLiveData.getValue();
    }

    public MutableLiveData<PagingDataSource<T>> getDataSource(){
        return mutableLiveData;
    }
}
