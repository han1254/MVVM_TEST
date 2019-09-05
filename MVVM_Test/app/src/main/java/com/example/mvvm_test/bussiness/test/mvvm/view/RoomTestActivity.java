package com.example.mvvm_test.bussiness.test.mvvm.view;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mvvm_test.R;

import com.example.mvvm_test.bussiness.test.mvvm.adapter.PagingAdapter;

import com.example.mvvm_test.bussiness.test.mvvm.model.Person;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.MyViewModel;
import com.example.mvvm_test.bussiness.test.mvvm.viewmodel.RoomTestViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomTestActivity extends AppCompatActivity {


    private List<Person> personlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private LiveData<PagedList<Person>> mLiveData;

    private RoomTestViewModel viewModel;
    private PagingAdapter adapter;
    private Button btnSave;
    private EditText edtName;
    private EditText edtAge;
    private EditText edtId;
    private ExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        initData();
        initView();
    }

    public void initData(){
        viewModel = new RoomTestViewModel();
        adapter = new PagingAdapter(this);
        adapter.setViewModel(viewModel);

    }




    public void initView(){

        btnSave = (Button)findViewById(R.id.save);
        edtName = (EditText)findViewById(R.id.edt_name);
        edtAge = (EditText)findViewById(R.id.edt_age);
        edtId = (EditText)findViewById(R.id.edt_id);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().trim().equals("")||edtAge.getText().toString().trim().equals("")||edtId.getText().toString().trim().equals("")){
                    Toast.makeText(RoomTestActivity.this,"没写全",Toast.LENGTH_LONG).show();
                }else{

                    String sage = edtAge.getText().toString().trim();
                    String sId = edtId.getText().toString().trim();
                    int age = Integer.parseInt(sage);
                    int id = Integer.parseInt(sId);
                    Person person = new Person(edtName.getText().toString().trim(),age,id);

                    mExecutorService = Executors.newCachedThreadPool();
                    mExecutorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.savePerson(person);
                        }
                    });

                }
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.room_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
