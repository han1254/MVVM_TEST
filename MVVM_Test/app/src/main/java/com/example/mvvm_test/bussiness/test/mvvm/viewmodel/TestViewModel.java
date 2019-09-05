package com.example.mvvm_test.bussiness.test.mvvm.viewmodel;

import com.example.mvvm_test.base.baseviewmodel.BaseViewModel;
import com.example.mvvm_test.base.paging.PagingDataSource;
import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.bussiness.test.repository.TestRepository;

public class TestViewModel extends BaseViewModel<CalendarModel> {
    @Override
    public PagingDataSource<CalendarModel> getDataSource() {

        return new PagingDataSource<>(this,TestRepository.getInstance());
    }
}
