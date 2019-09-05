package com.example.mvvm_test.bussiness.test.repository;

import android.arch.paging.DataSource;

import com.example.mvvm_test.Callback;
import com.example.mvvm_test.data.DBController;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    List<Person> personlist = new ArrayList<>();
    private  android.arch.paging.DataSource.Factory<Integer, Person> factory;
//    private final PersonDao personDao;
//
//    public Repository(PersonDao personDao) {
//
//        this.personDao = personDao;
//    }
//
//    public LiveData<List<Person>> getList(){
//        return personDao.getAll();
//    }
//
//    public LiveData<Person> getPersonById(int id){
//        return personDao.getPeronById(id);
//    }
//
//    public void createNewItem(Person person){
//        personDao.insertPerson(person);
//
//    }
//
//    public void deleteItem(Person person){
//        personDao.deletPerson(person);
//    }
//

    private Repository(){

    }

    private static class RepositoryHolder{
        static Repository instance = new Repository();
    }

    public static Repository getInstance(){
        return RepositoryHolder.instance;
    }

    public android.arch.paging.DataSource.Factory<Integer, Person> getDataSource(){
        DBController.getInstance().getPersonDataSource(new Callback<DataSource.Factory<Integer, Person>>() {
            @Override
            public void onCallback(android.arch.paging.DataSource.Factory<Integer, Person> integerPersonFactory) {
                factory = integerPersonFactory;
            }
        });

        return factory;
    }


    public void recieveData(){
        Person person1 = new Person("张三",10,1);
        personlist.add(person1);
        Person person2 = new Person("李四",12,2);
        personlist.add(person2);
        Person person3 = new Person("王五",13,3);
        personlist.add(person3);
        Person person4 = new Person("赵六",16,4);
        personlist.add(person4);

        DBController.getInstance().savePersons(personlist);
    }




}

