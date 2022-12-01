package com.example.lms.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;

public class NoticeDetailActivity extends AppCompatActivity {
    TextView notice_detail_title, notice_detail_writer, notice_detail_readcnt, notice_detail_writedate, notice_detail_content, notice_detail_filename;
    ImageView notice_detail_filepath;
    Button notice_list, notice_modify, notice_delete;
    NoticeFragment fragment = new NoticeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        Intent intent = getIntent();
        NoticeVO vo = (NoticeVO) intent.getSerializableExtra("vo");
        notice_detail_title = findViewById(R.id.notice_detail_title);
        notice_detail_writer = findViewById(R.id.notice_detail_writer);
        notice_detail_readcnt = findViewById(R.id.notice_detail_readcnt);
        notice_detail_writedate = findViewById(R.id.notice_detail_writedate);
        notice_detail_content = findViewById(R.id.notice_detail_content);
        notice_detail_filename = findViewById(R.id.notice_detail_filename);
        notice_detail_filepath = findViewById(R.id.notice_detail_filepath);

        notice_list = findViewById(R.id.notice_list);
        notice_modify = findViewById(R.id.notice_modify);
        notice_delete = findViewById(R.id.notice_delete);


        if (CommonVal.loginInfo.getInfo_cd() == 3){
            notice_modify.setVisibility(View.VISIBLE);
            notice_delete.setVisibility(View.VISIBLE);
        }else{
            notice_modify.setVisibility(View.GONE);
            notice_delete.setVisibility(View.GONE);
        }


        notice_detail_title.setText(vo.getTitle());
        notice_detail_writer.setText(vo.getWriter());
        notice_detail_readcnt.setText(vo.getReadcnt()+"");
        notice_detail_writedate.setText(vo.getWritedate().substring(0, vo.getWritedate().indexOf(" ")));
        notice_detail_content.setText(vo.getContent());
        if(vo.getFilename() != null) {
            notice_detail_filename.setText(vo.getFilename());
        }


        if(vo.getFilepath() == null) {
            notice_detail_filepath.setVisibility(View.INVISIBLE);
        }


        notice_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        notice_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(NoticeDetailActivity.this, NoticeModifyActivity.class);
                intent1.putExtra("vo", vo);
                startActivity(intent1);

            }
        });

        notice_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("anddelete.no", NoticeDetailActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult) {
                            finish();
                        }
                    }
                });


            }
        });

    }
}