package com.example.lms.lecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

public class LectureModifyActivity extends AppCompatActivity {
    EditText lecture_room, teacher_name, lecture_title, lecture_year, semester,
            subjectcredit, book, lecture_day, lecture_time, enrolment, capacity, midex, finalex,
            state, sortation, reception_status;
    TextView lecture_num;
    Button btn_save, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_modify);

        Intent intent = getIntent();
        LectureVO vo = (LectureVO) intent.getSerializableExtra("vo");
        lecture_num = findViewById(R.id.lecture_num);
        lecture_room = findViewById(R.id.lecture_room);
        teacher_name = findViewById(R.id.teacher_name);
        lecture_title = findViewById(R.id.lecture_title);
        lecture_year = findViewById(R.id.lecture_year);
        semester = findViewById(R.id.semester);
        subjectcredit = findViewById(R.id.subjectcredit);
        book = findViewById(R.id.book);
        lecture_day = findViewById(R.id.lecture_day);
        lecture_time = findViewById(R.id.lecture_time);
        //enrolment = findViewById(R.id.enrolment);
        capacity = findViewById(R.id.capacity);
        midex = findViewById(R.id.midex);
        finalex = findViewById(R.id.finalex);
        state = findViewById(R.id.state);
        sortation = findViewById(R.id.sortation);
        //reception_status = findViewById(R.id.reception_status);


        lecture_num.setText(vo.getLecture_num() + "");
        lecture_room.setText(vo.getLecture_room());
        teacher_name.setText(vo.getTeacher_name());
        lecture_title.setText(vo.getLecture_title());
        lecture_year.setText(vo.getLecture_year());
        semester.setText(vo.getSemester());
        subjectcredit.setText(vo.getSubjectcredit());
        book.setText(vo.getBook());
        lecture_day.setText(vo.getLecture_day());
        lecture_time.setText(vo.getLecture_time());
        //enrolment.setText(vo.getEnrolment());
        capacity.setText(vo.getCapacity() + "");
        midex.setText(vo.getMidex() + "");
        finalex.setText(vo.getFinalex() + "");
        state.setText(vo.getState());
        sortation.setText(vo.getSortation());
        //reception_status.setText(vo.getReception_status());

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("andupdate.lec", LectureModifyActivity.this);
                vo.setLecture_num(Integer.parseInt(lecture_num.getText() + ""));
                vo.setLecture_room (lecture_room.getText()+"");
                vo.setTeacher_name (teacher_name.getText()+"");
                vo.setLecture_title(lecture_title.getText()+"");
                vo.setLecture_year (lecture_year.getText()+"");
                vo.setSemester     (semester.getText()+"");
                vo.setSubjectcredit(subjectcredit.getText()+"");
                vo.setBook         (book.getText()+"");
                vo.setLecture_day  (lecture_day.getText()+"");
                vo.setLecture_time (lecture_time .getText()+"");
//                vo.setEnrolment    (enrolment.getText()+"");
                vo.setCapacity     (capacity.getText()+"");
//                vo.setMidex        ( midex.getText());
//                vo.setFinalex      (finalex.getText());
                vo.setState        (state.getText()+"");
                vo.setSortation    (sortation.getText()+"");
//                vo.setReception_status( reception_status.getText()+"");


                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult) {
                            finish();
                        } else {
                            Toast.makeText(LectureModifyActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                        //return null;
                    }
                });
            }
        });
    }
}