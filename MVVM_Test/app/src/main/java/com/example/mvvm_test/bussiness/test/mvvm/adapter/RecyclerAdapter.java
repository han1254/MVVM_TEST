package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.model.Person;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    private Context context;
    private List<Person> personList = new ArrayList<>();

    public RecyclerAdapter(Context context, List<Person> datalist){
        this.context = context;
        this.personList = datalist;
    }

    @NonNull
    @Override
    public RecyclerAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.VH vh, int i) {
        vh.mTvage.setText(String.valueOf(personList.get(i).getAge()));
        vh.mTvName.setText(personList.get(i).getName());
        vh.mTvId.setText(String.valueOf(personList.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private TextView mTvName;
        private TextView mTvage;
        private TextView mTvId;


        public VH(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.item_name);
            mTvage = itemView.findViewById(R.id.item_age);
            mTvId = itemView.findViewById(R.id.item_id);

        }
    }
}

