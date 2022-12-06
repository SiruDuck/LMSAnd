package com.example.lms.myinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyinfoFragment extends Fragment {
    TextView info_id,info_name, info_department_name, info_grade, info_state, info_start_date, info_phone, info_email, info_addr, info_post, start_date;
    CircleImageView info_profile;
    String id, profile;
    Button myinfo_update;
    MemberVO vo = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, container, false);
        info_id = v.findViewById(R.id.info_id); info_name = v.findViewById(R.id.info_name); info_department_name = v.findViewById(R.id.info_department_name);
        info_grade = v.findViewById(R.id.info_grade); info_state = v.findViewById(R.id.info_state   ); info_start_date = v.findViewById(R.id.info_start_date);
        info_phone = v.findViewById(R.id.info_phone); info_email = v.findViewById(R.id.info_email); info_addr = v.findViewById(R.id.info_addr);
        start_date = v.findViewById(R.id.start_date);
        info_profile = v.findViewById(R.id.info_profile);
        myinfo_update = v.findViewById(R.id.myinfo_update);

        id = CommonVal.loginInfo.getId();

        Log.d("태그", "onCreateView: id값"+id);

        info_id.setText(id);


        profile = CommonVal.loginInfo.getProfile();

        Log.d("프로필", "onCreateView: "+ profile);

        if (CommonVal.loginInfo.getProfile() == null){
            Glide.with(getContext()).load("http://112.164.58.181:3301/lms/upload/profile/2022/11/24/student.png").into(info_profile);
        }else{
            Glide.with(getContext()).load(profile).into(info_profile);
        }


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

        myinfo_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MyinfoUpdateActivity.class);
                intent1.putExtra("vo", vo);
                startActivity(intent1);
            }
        });









        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        cus_select(  CommonVal.loginInfo.getId(),new CommonAskTask.AsynckTaskCallback() {
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
        } );

    }
public void test(ArrayList<InfoMemberVO> list){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    info_name.setText(list.get(0).getName());

    info_department_name.setText(list.get(0).getDepartment_name());
    if(list.get(0).getInfo_name().equals("교수")){
        info_grade.setText("교수");
        start_date.setText("입사일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
    }else if(list.get(0).getInfo_name().equals("관리자")){
        info_grade.setText("관리자");
        start_date.setText("입사일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
    }else{
        info_grade.setText(list.get(0).getGrade());
        start_date.setText("입학일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
    }
    //info_grade.setText(list.get(0).getGrade());
    info_state.setText(list.get(0).getState());
    info_start_date.setText("생년월일: "+list.get(0).getBirth().substring(0, list.get(0).getStart_date().indexOf(" ")));
    info_phone.setText(list.get(0).getPhone());
    info_email.setText(list.get(0).getEmail());
    info_addr.setText(list.get(0).getAddr());
    

}

    public void cus_select(String id , CommonAskTask.AsynckTaskCallback callback){
        ArrayList<InfoMemberVO> list;
        CommonAskTask task = new CommonAskTask("appmyinfo", getContext());
        task.addParam("id",id);
        task.executeAsk(callback);

    }

}