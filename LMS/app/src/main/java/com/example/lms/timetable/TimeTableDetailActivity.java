package com.example.lms.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lms.R;

//정보 상세보기
public class TimeTableDetailActivity extends AppCompatActivity {
    TextView lecture_num, lecture_title, sortation, lecture_room, capacity, teacher_name, lecture_time, subjectcredit, state, book;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_time_table_detail);

        TimeTableVO table_vo = (TimeTableVO) getIntent().getSerializableExtra("list");

        btn_ok = findViewById(R.id.btn_ok);
        lecture_num = findViewById(R.id.lecture_num);
        lecture_title = findViewById(R.id.lecture_title);
        sortation = findViewById(R.id.sortation);
        lecture_room = findViewById(R.id.lecture_room);
        capacity = findViewById(R.id.capacity);
        teacher_name = findViewById(R.id.teacher_name);
        lecture_time = findViewById(R.id.lecture_time);
        subjectcredit = findViewById(R.id.subjectcredit);
        state = findViewById(R.id.state);
        book = findViewById(R.id.book);

        lecture_num.setText(table_vo.getLecture_num()+"");
        lecture_title.setText(table_vo.getLecture_title());
        sortation.setText(table_vo.getSortation());
        lecture_room.setText(table_vo.getLecture_room());
        capacity.setText(table_vo.getCapacity() + "명");
        teacher_name.setText(table_vo.getTeacher_name());
        lecture_time.setText(table_vo.getLecture_time());
        subjectcredit.setText(table_vo.getSubjectcredit() + "학점");
        state.setText(table_vo.getState());
        book.setText(table_vo.getBook());

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}