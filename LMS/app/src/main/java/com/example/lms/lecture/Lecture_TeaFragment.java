package com.example.lms.lecture;

import static com.example.lms.lms.CommonVal.loginInfo;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.member.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class Lecture_TeaFragment extends Fragment {
    RecyclerView recv_tea;
    TextView teacher_name, lecture_num;
    List<LectureVO> list;
    CardView lec_tea_stu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture__tea, container, false);
        //lec_tea_stu = v.findViewById(R.id.lec_tea_stu);
        Log.d("TAG", "강귄강귄강귄강귄강귄강귄강귄강귄강귄강귄강귄강귄");

        recv_tea = v.findViewById(R.id.recv_tea);
        teacher_name = v.findViewById(R.id.teacher_name);
        int i = 0;
        //teacher_name.setText(list.get(i).getTeacher_name());
        Log.d("loginInfo", "name: "+loginInfo.getName() );
        //teacher_name.setText( loginInfo.getName() );
        final int index = i ;



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        teacher_lec_list();
    }

    public void teacher_lec_list(){
        CommonAskTask task = new CommonAskTask("and_teacher_lec_list.lec", getContext());
        task.addParam("loginInfo", loginInfo);
        task.addParam("vo", loginInfo.getName());
        task.addParam("id", loginInfo.getId());
//        task.addParam("vo", teacher_name);
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {

            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    
                    ArrayList<LectureVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<LectureVO>>(){}.getType());
                    LectureMyAdapter adapter = new LectureMyAdapter(getLayoutInflater(), list, getActivity());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext(), RecyclerView.VERTICAL, false
                    );


                    recv_tea.setAdapter(adapter);
                    recv_tea.setLayoutManager(manager);
                }else{

                }
                //return null;
            }
        });
    }
}