package com.example.lms.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lms.R;
import com.example.lms.board.BoardVO;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyinfoUpdateActivity extends AppCompatActivity {
    Button myinfoupdate_btn, myinfoupdate_cancle;
    TextView infoupdate_id, infoupdate_name, infoupdate_department_name, infoupdate_grade, infoupdate_state, infoupdate_start_date, startupdate_date;
    CircleImageView infoupdate_profile;
    EditText infoupdate_phone, infoupdate_email, infoupdate_addr;
    String id, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo_update);

        final Intent intent = getIntent();
        MemberVO vo = new MemberVO();


        infoupdate_id = findViewById(R.id.infoupdate_id);
        infoupdate_department_name = findViewById(R.id.infoupdate_department_name);
        infoupdate_name = findViewById(R.id.infoupdate_name);
        infoupdate_grade = findViewById(R.id.infoupdate_grade);
        infoupdate_state = findViewById(R.id.infoupdate_state);
        startupdate_date = findViewById(R.id.startupdate_date);
        infoupdate_start_date = findViewById(R.id.infoupdate_start_date);
        infoupdate_profile = findViewById(R.id.infoupdate_profile);
        infoupdate_phone = findViewById(R.id.infoupdate_phone);
        infoupdate_email = findViewById(R.id.infoupdate_email);
        infoupdate_addr = findViewById(R.id.infoupdate_addr);

        myinfoupdate_btn = findViewById(R.id.myinfoupdate_btn);
        myinfoupdate_cancle = findViewById(R.id.myinfoupdate_cancle);


        myinfoupdate_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = CommonVal.loginInfo.getId();
        infoupdate_id.setText(id);

        profile = CommonVal.loginInfo.getProfile();



        if (CommonVal.loginInfo.getProfile() == null){
            Glide.with(this).load("http://112.164.58.181:3301/lms/upload/profile/2022/11/24/student.png").into(infoupdate_profile);
        }else{
            Glide.with(this).load(profile).into(infoupdate_profile);
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


        cus_select(id, callback);




        myinfoupdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setPhone(infoupdate_phone.getText()+"");
                vo.setEmail(infoupdate_email.getText()+"");
                vo.setAddr(infoupdate_addr.getText()+"");
                vo.setId(CommonVal.loginInfo.getId());
                CommonAskTask task = new CommonAskTask("andupdate.myinfo", MyinfoUpdateActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult){
                            finish();
                        }
                    }
                });





            }
        });



    }


    public void test(ArrayList<InfoMemberVO> list){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        infoupdate_name.setText(list.get(0).getName());

        infoupdate_department_name.setText(list.get(0).getDepartment_name());
        if(list.get(0).getInfo_name().equals("교수")){
            infoupdate_grade.setText("교수");
            startupdate_date.setText("입사일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
        }else if(list.get(0).getInfo_name().equals("관리자")){
            infoupdate_grade.setText("관리자");
            startupdate_date.setText("입사일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
        }else{
            infoupdate_grade.setText(list.get(0).getGrade());
            startupdate_date.setText("입학일 : "+list.get(0).getStart_date().substring(0, list.get(0).getStart_date().indexOf(" ")));
        }
        //info_grade.setText(list.get(0).getGrade());
        infoupdate_state.setText(list.get(0).getState());
        infoupdate_start_date.setText("생년월일: "+list.get(0).getBirth().substring(0, list.get(0).getStart_date().indexOf(" ")));
        infoupdate_phone.setText(list.get(0).getPhone());
        infoupdate_email.setText(list.get(0).getEmail());
        infoupdate_addr.setText(list.get(0).getAddr());


    }

    public void cus_select(String id , CommonAskTask.AsynckTaskCallback callback){
        ArrayList<InfoMemberVO> list;
        CommonAskTask task = new CommonAskTask("appmyinfo", this);
        task.addParam("id",id);
        task.executeAsk(callback);

    }




}