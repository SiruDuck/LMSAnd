package com.example.lms.notice;

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

public class NoticeFragment extends Fragment {
    RecyclerView notice_recv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notice, container, false);
        notice_recv = v.findViewById(R.id.notice_recv);










        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        notice_select();
    }

    public void notice_select(){

        CommonAskTask task = new CommonAskTask("notice.list" , getContext());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                // 통신 완료 시 데이터를 가지고 온다.
                if(isResult){
                    ArrayList<NoticeVO> list =
                            new Gson().fromJson(data , new TypeToken<ArrayList<NoticeVO>>(){}.getType());

                    NoticeAdapter adapter = new NoticeAdapter(getLayoutInflater() , list , NoticeFragment.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext() , RecyclerView.VERTICAL , false
                    );
                    notice_recv.setAdapter(adapter);
                    notice_recv.setLayoutManager(manager);



                }else {
                    Log.d("고객", "onResult:Fail " + data);
                }
            }
        });

    }








}