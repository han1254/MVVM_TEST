package com.example.mvvm_test.network;

import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.network.response.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //获得自己订阅的黄历
    @GET("calendar/subscribed")
    Call<ApiResponse<List<CalendarModel>>> getSubscribed();

}
