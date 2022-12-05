package com.example.lms.timetable;

import android.os.Bundle;
import static com.example.lms.lms.CommonVal.loginInfo;
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
    EnrolmentVO enrol_vo;
    ArrayList<TextView> monList;
    ArrayList<TextView> tueList;
    ArrayList<TextView> wedList;
    ArrayList<TextView> thuList;
    ArrayList<TextView> friList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time_table, container, false);

        monList = new ArrayList<>();
        tueList = new ArrayList<>();
        wedList = new ArrayList<>();
        thuList = new ArrayList<>();
        friList = new ArrayList<>();

        monList.add(v.findViewById(R.id.mon1));
        monList.add(v.findViewById(R.id.mon2));
        monList.add(v.findViewById(R.id.mon3));
        monList.add(v.findViewById(R.id.mon4));
        monList.add(v.findViewById(R.id.mon5));
        monList.add(v.findViewById(R.id.mon6));
        monList.add(v.findViewById(R.id.mon7));
        monList.add(v.findViewById(R.id.mon8));

        tueList.add(v.findViewById(R.id.tue1));
        tueList.add(v.findViewById(R.id.tue2));
        tueList.add(v.findViewById(R.id.tue3));
        tueList.add(v.findViewById(R.id.tue4));
        tueList.add(v.findViewById(R.id.tue5));
        tueList.add(v.findViewById(R.id.tue6));
        tueList.add(v.findViewById(R.id.tue7));
        tueList.add(v.findViewById(R.id.tue8));

        wedList.add(v.findViewById(R.id.wed1));
        wedList.add(v.findViewById(R.id.wed2));
        wedList.add(v.findViewById(R.id.wed3));
        wedList.add(v.findViewById(R.id.wed4));
        wedList.add(v.findViewById(R.id.wed5));
        wedList.add(v.findViewById(R.id.wed6));
        wedList.add(v.findViewById(R.id.wed7));
        wedList.add(v.findViewById(R.id.wed8));

        thuList.add(v.findViewById(R.id.thu1));
        thuList.add(v.findViewById(R.id.thu2));
        thuList.add(v.findViewById(R.id.thu3));
        thuList.add(v.findViewById(R.id.thu4));
        thuList.add(v.findViewById(R.id.thu5));
        thuList.add(v.findViewById(R.id.thu6));
        thuList.add(v.findViewById(R.id.thu7));
        thuList.add(v.findViewById(R.id.thu8));

        friList.add(v.findViewById(R.id.fri1));
        friList.add(v.findViewById(R.id.fri2));
        friList.add(v.findViewById(R.id.fri3));
        friList.add(v.findViewById(R.id.fri4));
        friList.add(v.findViewById(R.id.fri5));
        friList.add(v.findViewById(R.id.fri6));
        friList.add(v.findViewById(R.id.fri7));
        friList.add(v.findViewById(R.id.fri8));




        table_list();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        table_list();
    }

    public void table_list(){
        CommonAskTask task = new CommonAskTask("table.at", getContext());
        task.addParam("id", loginInfo.getId());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if (isResult){
                    Log.d("lms", "onResult: 시간표" + data);
                     ArrayList<TimeTableVO> list = new Gson().fromJson(data , new TypeToken<ArrayList<TimeTableVO>>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        if(list.get(i).getLecture_day().equals("월")){
                            monList.get(Integer.parseInt(list.get(i).getLecture_time())-1).setText(list.get(i).getLecture_title());
                        }else if(list.get(i).getLecture_day().equals("화")){
                            tueList.get(Integer.parseInt(list.get(i).getLecture_time())-1).setText(list.get(i).getLecture_title());
                        }else if(list.get(i).getLecture_day().equals("수")){
                            wedList.get(Integer.parseInt(list.get(i).getLecture_time())-1).setText(list.get(i).getLecture_title());
                        }else if(list.get(i).getLecture_day().equals("목")){
                            thuList.get(Integer.parseInt(list.get(i).getLecture_time())-1).setText(list.get(i).getLecture_title());
                        }else if(list.get(i).getLecture_day().equals("금")){
                            friList.get(Integer.parseInt(list.get(i).getLecture_time())-1).setText(list.get(i).getLecture_title());
                        }
                    }
                }else{
                    Log.d("lms", "onResult:Fail " + data);
                }
            }
        });
    }
}