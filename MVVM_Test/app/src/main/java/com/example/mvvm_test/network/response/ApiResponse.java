package com.example.mvvm_test.network.response;

public class ApiResponse<T> {


    /**
     * code : 10002
     * data : 89
     * message : 用户名已经存在
     */

    private int code;
    private T data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
