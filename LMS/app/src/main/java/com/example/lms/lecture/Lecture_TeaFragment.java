package com.example.lms.lecture;

import android.content.Intent;
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
import com.example.lms.member.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Member;
import java.util.ArrayList;


public class Lecture_TeaFragment extends Fragment {
    RecyclerView recv_lecture;
    String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture__tea, container, false);
        name = this.getArguments().getString("Name");
        Log.d("태그", "onCreateView: "+name);
        recv_lecture = v.findViewById(R.id.recv_lecture);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        teacher_lec_list();
    }

    public void teacher_lec_list(){
        CommonAskTask task = new CommonAskTask("and_teacher_lec_list.lec", getContext());
        Log.d("로그", "teacher_lec_list: "+name);
        task.addParam("name", name);
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {

            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){

                    ArrayList<LectureVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<LectureVO>>(){}.getType());
                    LectureMyAdapter adapter = new LectureMyAdapter(getLayoutInflater(), list, getActivity());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext(), RecyclerView.VERTICAL, false
                    );


                    recv_lecture.setAdapter(adapter);
                    recv_lecture.setLayoutManager(manager);
                }else{

                }
            }
        });
    }
}