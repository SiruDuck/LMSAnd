package com.example.lms.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

public class BoardDetailActivity extends AppCompatActivity {
    TextView board_detail_title, board_detail_writer, board_detail_readcnt, board_detail_writedate, board_detail_content, board_detail_filename;
    ImageView board_detail_filepath;
    Button board_list, board_modify, board_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");
        board_detail_title = findViewById(R.id.board_detail_title);
        board_detail_writer = findViewById(R.id.board_detail_writer);
        board_detail_readcnt = findViewById(R.id.board_detail_readcnt);
        board_detail_writedate = findViewById(R.id.board_detail_writedate);
        board_detail_content = findViewById(R.id.board_detail_content);
        board_detail_filename = findViewById(R.id.board_detail_filename);
        board_detail_filepath = findViewById(R.id.board_detail_filepath);
        board_list = findViewById(R.id.board_list);
        board_modify = findViewById(R.id.board_modify);
        board_delete = findViewById(R.id.board_delete);



        board_detail_title.setText(vo.getTitle()+"");
        board_detail_writer.setText(vo.getWriter()+"");
        board_detail_readcnt.setText(vo.getReadcnt()+ "");
        board_detail_writedate.setText(vo.getWritedate()+"");
        board_detail_content.setText(vo.getContent()+"");


        if(vo.getFilename() != null) {
            board_detail_filename.setText(vo.getFilename());
        }

        if(vo.getFilepath() == null) {
            board_detail_filepath.setVisibility(View.INVISIBLE);
        }


        board_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        board_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BoardDetailActivity.this, BoardModifyActivity.class);
                intent1.putExtra("vo", vo);




                startActivity(intent1);

            }
        });

        board_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("anddelete.bo", BoardDetailActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult){
                            finish();
                        }
                    }
                });



            }
        });


    }
}