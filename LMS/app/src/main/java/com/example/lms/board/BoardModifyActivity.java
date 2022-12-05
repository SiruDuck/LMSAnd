package com.example.lms.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

public class BoardModifyActivity extends AppCompatActivity {
    EditText board_modify_title,  board_modify_content;
    Button board_modify_save, board_modify_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_modify);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");

        board_modify_title = findViewById(R.id.board_modify_title);
        board_modify_content = findViewById(R.id.board_modify_content);
        board_modify_save = findViewById(R.id.board_modify_save);
        board_modify_cancel = findViewById(R.id.board_modify_cancel);


        board_modify_title.setText(vo.getTitle());
        board_modify_content.setText(vo.getContent());



        board_modify_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(board_modify_title.getText()+ "");
                vo.setContent(board_modify_content.getText()+"");

                CommonAskTask task = new CommonAskTask("andupdate.bo", BoardModifyActivity.this);
                task.addParam("vo", new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult){
                            /* //12.05
                            Intent intent1 = new Intent(BoardModifyActivity.this, BoardDetailActivity.class);
                            intent1.putExtra("vo",vo);
                            startActivity(intent1);
                            */
                            //12.05 ---
                            Intent intent1 = new Intent();
                            intent1.putExtra("vo",vo);
                            setResult(RESULT_OK, intent1);
                            finish();
                            //----------
                        }

                    }
                });

            }
        });





        board_modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}