package com.example.lms.notice;



import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {
    RecyclerView notice_recv;
    FloatingActionButton notice_btn_add;
    NoticeVO vo = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notice, container, false);
        notice_recv = v.findViewById(R.id.notice_recv);
        notice_btn_add = v.findViewById(R.id.notice_btn_add);


        if(CommonVal.loginInfo.getInfo_cd() == 4) {
            notice_btn_add.setVisibility(View.VISIBLE);
        }else{
            notice_btn_add.setVisibility(View.GONE);
        }


        notice_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeInsertActivity.class);
                intent.putExtra("vo", vo);
                startActivity(intent);
            }
        });

        //notice_select();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        notice_select();
    }

    public void notice_select() {
        CommonAskTask askTask = new CommonAskTask("nolist" , getContext());
        askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("공지사항", "onResult: " + data);
                if (isResult) {
                    ArrayList<NoticeVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<NoticeVO>>() {
                            }.getType());
                    Log.d("공지사항", "onResult: " + list.size());

                    NoticeAdapter adapter = new NoticeAdapter(getLayoutInflater(), list, getContext(), (MainActivity) getActivity());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext() , RecyclerView.VERTICAL , false
                    );

                    notice_recv.setAdapter(adapter);
                    notice_recv.setLayoutManager(manager);
                }
            }
        });

    }




}