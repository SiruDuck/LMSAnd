package com.example.lms.board;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;

public class BoardDetailActivity extends AppCompatActivity {
    TextView board_detail_title, board_detail_writer, board_detail_readcnt, board_detail_writedate, board_detail_content, board_detail_filename;
    ImageView board_detail_filepath;
    Button board_list, board_modify, board_delete, reg_button;
    EditText comment_et;
    BoardCommentFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");
        BoardCommentVO vo2 = new BoardCommentVO();






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

        reg_button = findViewById(R.id.reg_button);
        comment_et = findViewById(R.id.comment_et);



        if (CommonVal.loginInfo.getName().equals( vo.getName() )) {
            board_modify.setVisibility(View.VISIBLE);
            board_delete.setVisibility(View.VISIBLE);
        }else{
            board_modify.setVisibility(View.GONE);
            board_delete.setVisibility(View.GONE);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame, new BoardCommentFragment(vo.getId())).commit();


        board_detail_title.setText(vo.getTitle()+"");
        board_detail_writer.setText(vo.getName()+"");
        board_detail_readcnt.setText(vo.getReadcnt()+ "");
        board_detail_writedate.setText(vo.getWritedate().substring(0, vo.getWritedate().indexOf(" ")));
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
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, new BoardFragment()).commit(); //12.05
                finish(); //12.05
            }
        });

        board_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BoardDetailActivity.this, BoardModifyActivity.class);
                intent1.putExtra("vo", vo);
                //startActivity(intent1); //12.05
                activityResultLauncher.launch(intent1);  //12.05

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



      reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo2.setBoard_id(Integer.parseInt(vo.getId()));
                vo2.setWriter(CommonVal.loginInfo.getName());
                vo2.setContent(comment_et.getText()+"");

                CommonAskTask task = new CommonAskTask("andcomment.insert", BoardDetailActivity.this);
                task.addParam("vo2", new Gson().toJson(vo2));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame, new BoardCommentFragment(vo.getId())).commit();
                        comment_et.setText("");
                    }
                });

            }
        });



    }

    //12.05 ----------
    ActivityResultLauncher<Intent> activityResultLauncher
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d("게시글상세", "상세화면으로 돌아옴");
            if(result.getResultCode() == Activity.RESULT_OK ) {
                Log.d("게시글상세", "확인시 돌아옴");
                //수정화면에서 수정저장하여 spring 에서 반환받은 DB에 저장된 고객정보를 출력
                Intent intent = result.getData();
                BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");

                board_detail_title.setText(vo.getTitle()+"");
                board_detail_writer.setText(vo.getWriter()+"");
                board_detail_readcnt.setText(vo.getReadcnt()+ "");
                board_detail_writedate.setText(vo.getWritedate().substring(0, vo.getWritedate().indexOf(" ")));
                board_detail_content.setText(vo.getContent()+"");


            }else if(result.getResultCode() == Activity.RESULT_CANCELED ){
                Log.d("게시글상세", "취소시 돌아옴");
            }
        }
    });
    //---------------





}