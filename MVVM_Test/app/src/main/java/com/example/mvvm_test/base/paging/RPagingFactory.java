package com.example.mvvm_test.base.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class RPagingFactory<T> extends DataSource.Factory<Integer,T> {


    private MutableLiveData<RPagingDatasource<T>> mDatasource = new MutableLiveData<>();

    public RPagingFactory(RPagingDatasource<T> datasource){
        mDatasource.setValue(datasource);
    }

    @Override
    public DataSource<Integer, T> create() {
        return mDatasource.getValue();
    }

    public MutableLiveData<RPagingDatasource<T>> getDataSource(){
        return mDatasource;
    }
}
