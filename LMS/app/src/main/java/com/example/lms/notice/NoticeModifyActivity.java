package com.example.lms.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lms.R;
import com.example.lms.board.BoardModifyActivity;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

public class NoticeModifyActivity extends AppCompatActivity {
    EditText notice_modify_title, notice_modify_content;
    Button notice_modify_save, notice_modify_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_modify);
        Intent intent = getIntent();
        NoticeVO vo = (NoticeVO) intent.getSerializableExtra("vo");

        notice_modify_title = findViewById(R.id.notice_modify_title);
        notice_modify_content = findViewById(R.id.notice_modify_content);
        notice_modify_save = findViewById(R.id.notice_modify_save);
        notice_modify_cancel = findViewById(R.id.notice_modify_cancel);

        notice_modify_title.setText(vo.getTitle());
        notice_modify_content.setText(vo.getContent());


        notice_modify_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(notice_modify_title.getText()+"");
                vo.setContent(notice_modify_content.getText()+"");

                CommonAskTask task = new CommonAskTask("andupdate.no", NoticeModifyActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult){
                            /*Intent intent1 = new Intent(NoticeModifyActivity.this, NoticeDetailActivity.class);
                            intent1.putExtra("vo", vo);
                            startActivity(intent1);*/
                            Intent intent1 = new Intent();
                            intent1.putExtra("vo", vo);
                            setResult(RESULT_OK, intent1);
                            finish();


                        }

                    }
                });



            }
        });


        notice_modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}