package com.example.mvvm_test.data;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.mvvm_test.Callback;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.runtime.AppRuntime;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBController {

    private PersonDataBase dataoBase;
    private ExecutorService executorService;

    private Context context;

    private DBController(){
        dataoBase = Room.databaseBuilder(AppRuntime.getAppContext(),PersonDataBase.class,"person_list")
                .fallbackToDestructiveMigration()
                .build();

        executorService = Executors.newCachedThreadPool();
    }

    private static class DBHolder{
        @SuppressLint("StaticFieldLeak")
        private static DBController instance = new DBController();
    }

    public static DBController getInstance(){
        return DBHolder.instance;
    }


    public void getPersonDataSource(final Callback<DataSource.Factory<Integer, Person>> callback){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                DataSource.Factory<Integer, Person> source = dataoBase.getPersonDao().getSource().map(new Function<Person, Person>() {
                    @Override
                    public Person apply(Person input) {
                        return input;
                    }
                });
                callback.onCallback(source);
            }
        });
    }


    public void savePerson(final Person person){
        if(person == null){
            return;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dataoBase.getPersonDao().insertPerson(person);
            }
        });
    }



    public void savePersons(final List<Person> list){
        if (list == null||list.isEmpty()){
            return;
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dataoBase.getPersonDao().insertList(list);
            }
        });
    }


}
