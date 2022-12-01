package com.example.lms.lecture;

import static com.example.lms.lms.CommonVal.loginInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Lecture_StuFragment extends Fragment {
    RecyclerView recv_stu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture__stu, container, false);
        recv_stu = v.findViewById(R.id.recv_stu);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        student_lec_list();
    }

    public void student_lec_list(){
        CommonAskTask task = new CommonAskTask("and_student_lec_list.lec", getContext());
        task.addParam("loginInfo", loginInfo);
        task.addParam("id", loginInfo.getId());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){

                    ArrayList<LectureVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<LectureVO>>(){}.getType());
                    LectureMyAdapter adapter = new LectureMyAdapter(getLayoutInflater(), list, getActivity());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext(), RecyclerView.VERTICAL, false
                    );


                    recv_stu.setAdapter(adapter);
                    recv_stu.setLayoutManager(manager);
                }else{

                }
            }
        });
    }
}