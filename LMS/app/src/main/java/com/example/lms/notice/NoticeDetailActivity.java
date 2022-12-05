package com.example.lms.notice;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.board.BoardVO;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;

import java.nio.channels.InterruptedByTimeoutException;

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


        if (CommonVal.loginInfo.getInfo_cd() == 4){
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

                activityResultLauncher.launch(intent1);

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


    ActivityResultLauncher<Intent> activityResultLauncher
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d("공지글상세", "상세화면으로 돌아옴");
            if(result.getResultCode() == Activity.RESULT_OK ) {
                Log.d("공지글상세", "확인시 돌아옴");
                //수정화면에서 수정저장하여 spring 에서 반환받은 DB에 저장된 고객정보를 출력
                Intent intent = result.getData();
                NoticeVO vo = (NoticeVO) intent.getSerializableExtra("vo");

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



            }else if(result.getResultCode() == Activity.RESULT_CANCELED ){
                Log.d("공지글상세", "취소시 돌아옴");
            }
        }
    });






}