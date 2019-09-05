package com.example.mvvm_test.base.paging;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.example.mvvm_test.base.api.PagingRepositoryCallback;
import com.example.mvvm_test.base.api.pagingCallback;
import com.example.mvvm_test.base.baseviewmodel.BaseViewModel;
import com.example.mvvm_test.base.model.BaseModel;
import com.example.mvvm_test.data.DBController;

import java.util.List;

public class RPagingDatasource<T> extends PositionalDataSource<T> {

    private PagingRepositoryCallback api;
    private BaseViewModel<T> mViewModel;

    public RPagingDatasource(PagingRepositoryCallback api, BaseViewModel<T> mViewModel){
        this.api = api;
        this.mViewModel = mViewModel;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<T> callback) {

        api.onGetList(0, 20, new pagingCallback() {
            @Override
            public void Succeed(List list) {
                callback.onResult(list,0);


            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<T> callback) {

    }
}
