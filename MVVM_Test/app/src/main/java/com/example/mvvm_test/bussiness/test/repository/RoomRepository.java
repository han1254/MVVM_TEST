package com.example.mvvm_test.bussiness.test.repository;

import com.example.mvvm_test.base.api.PagingRepositoryCallback;
import com.example.mvvm_test.base.api.pagingCallback;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.data.DBController;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements PagingRepositoryCallback {


    List<Person> personlist = new ArrayList<>();

    private RoomRepository(){

    }

    private static class RoomRepositoryHolder{
        private static final RoomRepository instance = new RoomRepository();
    }

    public static RoomRepository getInstance(){
        return RoomRepositoryHolder.instance;
    }


    @Override
    public void onGetList(int offset, int count, pagingCallback callBack) {

        Person person1 = new Person("张三",10,1);
        personlist.add(person1);
        Person person2 = new Person("李四",12,2);
        personlist.add(person2);
        Person person3 = new Person("王五",13,3);
        personlist.add(person3);
        Person person4 = new Person("赵六",16,4);
        personlist.add(person4);


    }


    public void addList(){
        DBController.getInstance().savePersons(personlist);
    }
}
