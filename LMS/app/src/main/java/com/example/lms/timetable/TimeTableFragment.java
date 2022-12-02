package com.example.lms.timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

// 내 시간표
public class TimeTableFragment extends Fragment {
    TimeTableVO table_vo;

    TextView mon1, mon2, mon3, mon4, mon5, mon6, mon7, mon8,
            tue1, tue2, tue3, tue4, tue5, tue6, tue7, tue8,
            wed1, wed2, wed3, wed4, wed5, wed6, wed7, wed8,
            thu1, thu2, thu3, thu4, thu5, thu6, thu7, thu8,
            fri1, fri2, fri3, fri4, fri5, fri6, fri7, fri8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_table, container, false);
        mon1 = v.findViewById(R.id.mon1);
        mon2 = v.findViewById(R.id.mon2);
        mon3 = v.findViewById(R.id.mon3);
        mon4 = v.findViewById(R.id.mon4);
        mon5 = v.findViewById(R.id.mon5);
        mon6 = v.findViewById(R.id.mon6);
        mon7 = v.findViewById(R.id.mon7);
        mon8 = v.findViewById(R.id.mon8);
        tue1 = v.findViewById(R.id.tue1);
        tue2 = v.findViewById(R.id.tue2);
        tue3 = v.findViewById(R.id.tue3);
        tue4 = v.findViewById(R.id.tue4);
        tue5 = v.findViewById(R.id.tue5);
        tue6 = v.findViewById(R.id.tue6);
        tue7 = v.findViewById(R.id.tue7);
        tue8 = v.findViewById(R.id.tue8);
        wed1 = v.findViewById(R.id.wed1);
        wed2 = v.findViewById(R.id.wed2);
        wed3 = v.findViewById(R.id.wed3);
        wed4 = v.findViewById(R.id.wed4);
        wed5 = v.findViewById(R.id.wed5);
        wed6 = v.findViewById(R.id.wed6);
        wed7 = v.findViewById(R.id.wed7);
        wed8 = v.findViewById(R.id.wed8);
        thu1 = v.findViewById(R.id.thu1);
        thu2 = v.findViewById(R.id.thu2);
        thu3 = v.findViewById(R.id.thu3);
        thu4 = v.findViewById(R.id.thu4);
        thu5 = v.findViewById(R.id.thu5);
        thu6 = v.findViewById(R.id.thu6);
        thu7 = v.findViewById(R.id.thu7);
        thu8 = v.findViewById(R.id.thu8);
        fri1 = v.findViewById(R.id.fri1);
        fri2 = v.findViewById(R.id.fri2);
        fri3 = v.findViewById(R.id.fri3);
        fri4 = v.findViewById(R.id.fri4);
        fri5 = v.findViewById(R.id.fri5);
        fri6 = v.findViewById(R.id.fri6);
        fri7 = v.findViewById(R.id.fri7);
        fri8 = v.findViewById(R.id.fri8);

        mon1.setText(table_vo.getLecture_time().equals("1") && table_vo.getLecture_day().equals("월") ? table_vo.getLecture_title()+"\n"+table_vo.getLecture_room() : "");
        mon2.setText(table_vo.getLecture_time().equals("2") && table_vo.getLecture_day().equals("월") ? table_vo.getLecture_title()+"\n"+table_vo.getLecture_room() : "");


        table_list();
        return v;
    }

    public void table_list(){
        CommonAskTask task = new CommonAskTask("table.at", getContext());
        task.addParam("id", "191002");
        task.addParam("lecture_num", table_vo.getLecture_num());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if (isResult){
                    Log.d("lms", "onResult: 시간표" + data);
                    ArrayList<EnrolmentVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<EnrolmentVO>>(){}.getType());
                }else{
                    Log.d("lms", "onResult:Fail " + data);
                }
            }
        });
    }
}