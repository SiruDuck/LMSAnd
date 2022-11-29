package com.example.lms.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MyinfoFragment extends Fragment {
    TextView info_id,info_name, info_department_name, info_grade, info_state, info_start_date, info_phone, info_email, info_addr, info_post, start_date;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, container, false);
        info_id = v.findViewById(R.id.info_id); info_name = v.findViewById(R.id.info_name); info_department_name = v.findViewById(R.id.info_department_name);
        info_grade = v.findViewById(R.id.info_grade); info_state = v.findViewById(R.id.info_state   ); info_start_date = v.findViewById(R.id.info_start_date);
        info_phone = v.findViewById(R.id.info_phone); info_email = v.findViewById(R.id.info_email); info_addr = v.findViewById(R.id.info_addr); info_post = v.findViewById(R.id.info_post);
        start_date = v.findViewById(R.id.start_date);

        id = CommonVal.loginInfo.getId();
        Log.d("태그", "onCreateView: id값"+id);

        info_id.setText(id);


        CommonAskTask.AsynckTaskCallback callback = new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    Log.d("로그", "onResult: "+data);
                    ArrayList<InfoMemberVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<InfoMemberVO>>(){}.getType());

                    test(list);





                }else{
                    Log.d("로그", "onResult:"+data);
                }
            }
        };
        
        cus_select(id,callback);








        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
public void test(ArrayList<InfoMemberVO> list){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    info_name.setText(list.get(0).getName());

    info_department_name.setText(list.get(0).getDepartment_name());
    if(list.get(0).getInfo_name().equals("교수")){
        info_grade.setText("교수");
    }else if(list.get(0).getInfo_name().equals("관리자")){
        info_grade.setText("관리자");
    }else{
        info_grade.setText(list.get(0).getGrade());
    }
    //info_grade.setText(list.get(0).getGrade());
    info_state.setText(list.get(0).getState());
    info_start_date.setText(list.get(0).getStart_date());
    info_phone.setText(list.get(0).getPhone());
    info_email.setText(list.get(0).getEmail());
    info_addr.setText(list.get(0).getAddr());
    info_post.setText(list.get(0).getPost());
    start_date.setText("startdate : "+simpleDateFormat.format(list.get(0).getStart_date()));

}

    public void cus_select(String id , CommonAskTask.AsynckTaskCallback callback){
        ArrayList<InfoMemberVO> list;
        CommonAskTask task = new CommonAskTask("appmyinfo", getContext());
        task.addParam("id",id);
        task.executeAsk(callback);

    }

}