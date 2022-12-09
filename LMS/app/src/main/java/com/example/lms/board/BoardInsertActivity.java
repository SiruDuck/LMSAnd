package com.example.lms.board;

import androidx.annotation.RequiresPermission;
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

import java.io.Writer;

public class BoardInsertActivity extends AppCompatActivity {
    EditText board_insert_title,  board_insert_content;
    Button board_insert_save, board_insert_cancel;
    private Object Writer;
    BoardCommentFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_insert);

        final Intent intent = getIntent();
        BoardVO vo = new BoardVO();
        Writer = intent.getSerializableExtra("Writer");

        board_insert_title = findViewById(R.id.board_insert_title);
        board_insert_content = findViewById(R.id.board_insert_content);
        board_insert_save = findViewById(R.id.board_insert_save);
        board_insert_cancel = findViewById(R.id.board_insert_cancel);


        board_insert_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(board_insert_title.getText()+"");
                vo.setContent(board_insert_content.getText()+"");
                vo.setId(CommonVal.loginInfo.getId());
                vo.setWriter(CommonVal.loginInfo.getName());

                CommonAskTask task = new CommonAskTask("andinsert.bo", BoardInsertActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult){
                            finish();
                        }
                    }
                });
                finish();
            }

        });


        board_insert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}