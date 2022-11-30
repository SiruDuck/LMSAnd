package com.example.lms.score;

import static com.example.lms.lms.CommonVal.loginInfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.member.LoginActivity;
import com.example.lms.member.MemberVO;
import com.example.lms.sidemenu.SideVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ScoreFragment extends Fragment {
    RecyclerView recv_score;
   /* ArrayList<ScoreVO> list;*/
    ArrayList<ScoreVO> list;
    ArrayList<ScoreVO> avglist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_score, container, false);
        list= new ArrayList<>();
        avglist= new ArrayList<>();

        CommonAskTask avgtask = new CommonAskTask("avg_sutdent_and.sc", getContext());
        avgtask.addParam("id",loginInfo.getId());
        avgtask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    TextView tv_subAvg = v.findViewById(R.id.tv_subAvg);
                    tv_subAvg.setText("총 평균: " + data + "          ");

                    CommonAskTask  task = new CommonAskTask("and_score_list.sc", getContext());
                    task.addParam("id", loginInfo.getId());
                    task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            if(isResult) {
                                list =
                                        new Gson().fromJson(data, new TypeToken<ArrayList<ScoreVO>>() {
                                        }.getType());
                                ScoreAdapter adapter = new ScoreAdapter(inflater,list,getContext());
                                RecyclerView.LayoutManager manager = new LinearLayoutManager(
                                        getContext(),RecyclerView.VERTICAL,false
                                );
                                recv_score = v.findViewById(R.id.recv_score);

                                recv_score.setAdapter(adapter);
                                recv_score.setLayoutManager(manager);
                            }
                        }
                    });
                }
            }
        });



        return v;
    }
}