package com.example.mvvm_test.base.api;

import com.example.mvvm_test.base.model.BaseModel;

import java.util.List;

public interface pagingCallback {
    void Succeed(List<? extends BaseModel> list);

    void onFailed(Exception e);
}
