package com.example.mvvm_test.bussiness.test.mvvm.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.mvvm_test.base.model.BaseModel;

@Entity(tableName = "person_list")
public class Person extends BaseModel {


    /**
     * name : 张三
     * age : 10
     */

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_id")
    public int id;
    @ColumnInfo(name = "person_name")
    public  String name;
    @ColumnInfo(name = "person_age")
    public  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Person(String name, int age, int id){
        this.name = name;
        this.age = age;
        this.id = id;
    }



}
