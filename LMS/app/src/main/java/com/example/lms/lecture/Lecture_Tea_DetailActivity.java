package com.example.lms.lecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.member.MemberVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Lecture_Tea_DetailActivity extends AppCompatActivity {
    TextView id, name, gender, grade, department, phone;
    RecyclerView lec_my_stu;
    Context context;
    CardView lec_tea_stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_tea_detail);

        Intent intent = getIntent();
        LectureVO vo = (LectureVO) intent.getSerializableExtra("vo");
        lec_my_stu = findViewById(R.id.lec_my_stu);


                CommonAskTask task = new CommonAskTask("and_teacher_stu.lec", Lecture_Tea_DetailActivity.this);

                task.addParam("lecture_num", new Gson().toJson(vo.getLecture_num()));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        ArrayList<LectureVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<LectureVO>>(){}.getType());
                        Lecture_Tea_StuAdapter adapter = new Lecture_Tea_StuAdapter(getLayoutInflater(), list, getParent());
                        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                                getParent(), RecyclerView.VERTICAL, false
                        );


                        lec_my_stu.setAdapter(adapter);
                        lec_my_stu.setLayoutManager(manager);
                    }
                });




    }
}