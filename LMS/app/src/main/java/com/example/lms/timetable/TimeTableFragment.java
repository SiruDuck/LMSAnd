package com.example.lms.timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

// 내 시간표 보기
public class TimeTableFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_table, container, false);

        table_list();
        return v;
    }

    public void table_list(){
        CommonAskTask task = new CommonAskTask("table.at", getContext());
        task.addParam("id", CommonVal.loginInfo.getId());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if (isResult){
                    Log.d("lms", "onResult: " + data);
                    ArrayList<EnrolmentVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EnrolmentVO>>(){}.getType());
                }else{
                    Log.d("lms", "onResult:Fail " + data);
                }
            }
        });
    }
}