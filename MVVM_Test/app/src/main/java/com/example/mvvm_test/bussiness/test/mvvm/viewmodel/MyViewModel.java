package com.example.mvvm_test.bussiness.test.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.mvvm_test.Callback;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.data.DBController;
import com.example.mvvm_test.bussiness.test.repository.Repository;

public class MyViewModel extends ViewModel {

    private LiveData<PagedList<Person>> livePersonList;
    private DataSource.Factory<Integer,Person> factory;

    final PagedList.Config Config = new PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(5)
            .setInitialLoadSizeHint(20)
            .build();



    public  LiveData<PagedList<Person>> getList(){


        factory = Repository.getInstance().getDataSource();

        DBController.getInstance().getPersonDataSource(new Callback<DataSource.Factory<Integer, Person>>() {
            @Override
            public void onCallback(DataSource.Factory<Integer, Person> integerPersonFactory) {
                factory = integerPersonFactory;
                livePersonList = new LivePagedListBuilder<>(Repository.getInstance().getDataSource(),Config).build();

            }
        });


        return livePersonList;
    }

}
