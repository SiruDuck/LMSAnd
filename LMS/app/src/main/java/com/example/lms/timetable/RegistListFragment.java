package com.example.lms.timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class RegistListFragment extends Fragment {
    RecyclerView rcv_regist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regist_list, container, false);
        rcv_regist = v.findViewById(R.id.rcv_regist);
        list_select();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        list_select();
    }

    public void list_select(){
        CommonAskTask task = new CommonAskTask("list.at", getContext());
        task.addParam("id", "191002");
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    Log.d("lms", "onResult: " + data);
                    ArrayList<TimeTableVO> table_list = new Gson().fromJson(data, new TypeToken<ArrayList<TimeTableVO>>(){}.getType());
                    RegistListAdapter adapter = new RegistListAdapter(getLayoutInflater(), table_list, RegistListFragment.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rcv_regist.setAdapter(adapter);
                    rcv_regist.setLayoutManager(manager);


                }else{
                    Log.d("lms", "onResult:Fail " + data);
                }
            }
        });
    }

}