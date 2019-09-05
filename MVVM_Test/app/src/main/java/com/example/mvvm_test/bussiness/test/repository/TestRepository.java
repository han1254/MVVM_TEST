package com.example.mvvm_test.bussiness.test.repository;

import com.example.mvvm_test.base.api.PagingRepositoryCallback;
import com.example.mvvm_test.base.api.pagingCallback;
import com.example.mvvm_test.bussiness.test.mvvm.model.CalendarModel;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.network.NetWorkFactory;
import com.example.mvvm_test.network.response.ApiResponse;
import com.example.mvvm_test.network.response.RspCallback;

import java.util.ArrayList;
import java.util.List;

public class TestRepository implements PagingRepositoryCallback {



    private TestRepository(){

    }

    private static class TestRepositoryHolder{
        private static final TestRepository instance = new TestRepository();
    }

    public static TestRepository getInstance(){
        return TestRepositoryHolder.instance;
    }

    @Override
    public void onGetList(int offset, int count, final pagingCallback callBack) {
//        NetWorkFactory.getApiService()
//                .getSubscribed()
//                .enqueue(new RspCallback<ApiResponse<List<CalendarModel>>>() {
//                    @Override
//                    public void onSuccess(ApiResponse<List<CalendarModel>> data) {
//                        callBack.Succeed(data.getData());
//                    }
//
//                    @Override
//                    public void onFailed(Throwable t) {
//
//                    }
//                });
//    }

        List<CalendarModel> list = new ArrayList<>();


            CalendarModel calendarModel1 = new CalendarModel();
            calendarModel1.setCalendarName("张三");
            calendarModel1.setCalendarId(1);
            list.add(calendarModel1);

            CalendarModel calendarModel2 = new CalendarModel();
            calendarModel2.setCalendarId(2);
            calendarModel2.setCalendarName("李四");
            list.add(calendarModel2);

            CalendarModel calendarModel3 = new CalendarModel();
            calendarModel3.setCalendarName("王五");
            calendarModel3.setCalendarId(3);
            list.add(calendarModel3);

//         List<Person> personlist = new ArrayList<>();
//
//        Person person1 = new Person("张三",10,1);
//        personlist.add(person1);
//        Person person2 = new Person("李四",12,2);
//        personlist.add(person2);
//        Person person3 = new Person("王五",13,3);
//        personlist.add(person3);
//        Person person4 = new Person("赵六",16,4);
//        personlist.add(person4);




        callBack.Succeed(list);

    }


}
