package com.example.mvvm_test.bussiness.test.repository;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.data.PersonDataBase;
import com.example.mvvm_test.runtime.AppRuntime;

import java.util.List;

public class RoomTestRepository {

    private static PersonDataBase dataBase;

    private RoomTestRepository(){
        dataBase = Room.databaseBuilder(AppRuntime.getAppContext(),PersonDataBase.class,"person_list").build();
    }

    private static class Holder{
        private static final RoomTestRepository INSTANCE = new RoomTestRepository();
    }

    public static RoomTestRepository getInstance(){
        return Holder.INSTANCE;
    }


    public DataSource.Factory<Integer, Person> getFactory(){
        return dataBase.getPersonDao().getSource();
    }

    public void saveData(Person person){
        dataBase.getPersonDao().insertPerson(person);
    }

    public void saveData(List<Person> personList){
        dataBase.getPersonDao().insertList(personList);
    }
}
