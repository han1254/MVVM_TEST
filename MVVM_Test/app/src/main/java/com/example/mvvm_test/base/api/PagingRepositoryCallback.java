package com.example.mvvm_test.base.api;

public interface PagingRepositoryCallback {
    void onGetList(int offset, int count, pagingCallback callBack);
}
