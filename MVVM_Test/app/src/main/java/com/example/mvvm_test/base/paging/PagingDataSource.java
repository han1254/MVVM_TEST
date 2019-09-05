package com.example.mvvm_test.base.paging;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.example.mvvm_test.base.api.PagingRepositoryCallback;
import com.example.mvvm_test.base.api.pagingCallback;
import com.example.mvvm_test.base.baseviewmodel.BaseViewModel;
import com.example.mvvm_test.base.model.BaseModel;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.data.DBController;

import java.util.ArrayList;
import java.util.List;

public class PagingDataSource<T> extends PositionalDataSource<T> {


    private BaseViewModel<T> mViewModel;
    private PagingRepositoryCallback api;

    public PagingDataSource(BaseViewModel<T> mViewModel, PagingRepositoryCallback api){
        this.mViewModel = mViewModel;
        this.api = api;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<T> callback) {

        api.onGetList(0, 20, new pagingCallback() {
            @Override
            public void Succeed(List list) {

                if(list.get(0) instanceof Person){
                    DBController.getInstance().savePersons(list);
                }

                callback.onResult(list,0);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<T> callback) {

        api.onGetList(0, 20, new pagingCallback() {
            @Override
            public void Succeed(List list) {
                callback.onResult(list);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }
}
