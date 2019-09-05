package com.example.mvvm_test.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mvvm_test.bussiness.test.mvvm.model.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PersonDao {

    @Query("SELECT *FROM person_list WHERE person_id = :id")
    LiveData<Person> getPeronById(int id);

    @Query("SELECT * FROM person_list")
    DataSource.Factory<Integer,Person> getSource();

    @Query("SELECT *FROM person_list order by person_id desc")
    LiveData<List<Person>> getAll();

    @Insert
    void insertPerson(Person person);

    @Insert
    void insertList(List<Person> list);

    @Delete
    void deletPerson(Person person);
}

