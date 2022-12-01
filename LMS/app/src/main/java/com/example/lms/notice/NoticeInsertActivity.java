package com.example.lms.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;

public class NoticeInsertActivity extends AppCompatActivity {
    EditText notice_insert_title, notice_insert_content;
    Button notice_insert_save, notice_insert_cancel;
    private Object Writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_insert);

        final Intent intent = getIntent();
        NoticeVO vo = new NoticeVO();//(NoticeVO) intent.getSerializableExtra("vo");
        Writer = intent.getSerializableExtra("Writer");

        notice_insert_title = findViewById(R.id.notice_insert_title);
        notice_insert_content = findViewById(R.id.notice_insert_content);
        notice_insert_save = findViewById(R.id.notice_insert_save);
        notice_insert_cancel = findViewById(R.id.notice_insert_cancel);

        notice_insert_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(notice_insert_title.getText()+ "");
                vo.setContent(notice_insert_content.getText()+"");
                vo.setId(CommonVal.loginInfo.getId());
                vo.setWriter(CommonVal.loginInfo.getName());
                CommonAskTask task = new CommonAskTask("andinsert.no", NoticeInsertActivity.this);
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

        notice_insert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });









    }
}