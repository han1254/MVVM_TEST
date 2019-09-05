package com.example.mvvm_test.base;

import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.network.NetWorkFactory;
import com.example.mvvm_test.network.response.ApiResponse;
import com.example.mvvm_test.network.response.RspCallback;

import java.util.List;

public class RetrofitRepository {
    private RetrofitRepository(){

    }

    private static class RetrofitRepositoryHolder{
        static RetrofitRepository instance = new RetrofitRepository();
    }

    public RetrofitRepository getInstacne(){
        return RetrofitRepositoryHolder.instance;
    }

    public void getDetail(){
        NetWorkFactory.getApiService()
                .getSubscribed()
                .enqueue(new RspCallback<ApiResponse<List<CalendarModel>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<CalendarModel>> data) {

                    }

                    @Override
                    public void onFailed(Throwable t) {

                    }
                });
    }
}
