package com.example.mvvm_test.bussiness.test.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.view.View;

import com.example.mvvm_test.base.baseviewmodel.BaseViewModel;
import com.example.mvvm_test.base.paging.PagingDataSource;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.bussiness.test.repository.RoomRepository;
import com.example.mvvm_test.bussiness.test.repository.RoomTestRepository;

import java.util.List;

public class RoomTestViewModel extends ViewModel {
    private LiveData<PagedList<Person>> listLiveData;

    public RoomTestViewModel(){
        listLiveData = getListLiveData();
    }

    private LiveData<PagedList<Person>> getListLiveData(){
        return new LivePagedListBuilder<>(RoomTestRepository.getInstance().getFactory(),getConfig()).build();
    }

    private PagedList.Config getConfig() {
        return new PagedList.Config.Builder()
                //配置分页加载的数量
                .setPageSize(20)
                //配置是否启动PlaceHolders(占位符)
                .setEnablePlaceholders(false)
                //初始化加载的数量
                .setInitialLoadSizeHint(20)
                .build();
    }

    public LiveData<PagedList<Person>> getData(){
        return listLiveData;
    }

    public void savePerson(Person person){
        RoomTestRepository.getInstance().saveData(person);
    }

    public void savePerson(List<Person> list){
        RoomTestRepository.getInstance().saveData(list);
    }

}
