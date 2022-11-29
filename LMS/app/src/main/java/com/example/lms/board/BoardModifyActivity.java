package com.example.lms.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lms.R;

public class BoardModifyActivity extends AppCompatActivity {
    EditText board_modify_title, board_modify_writer, board_modify_writedate, board_modify_content, board_modify_filename;
    ImageView board_modify_filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_modify);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");

        board_modify_title = findViewById(R.id.board_modify_title);
        board_modify_writer = findViewById(R.id.board_modify_writer);
        //board_modify_writedate = findViewById(R.id.);









    }
}