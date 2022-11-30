package com.example.lms.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends AppCompatActivity {
    EditText edtid, edtpw;
    Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtid = findViewById(R.id.edtid);
        edtpw = findViewById(R.id.edtpw);
        btnlogin = findViewById(R.id.btnlogin);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("andLogin", LoginActivity.this);
                task.addParam("id", edtid.getText());
                task.addParam("pw", edtpw.getText());
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(data == "다시 입력"){
                            Toast.makeText(LoginActivity.this, "아이디/비번이 잘못되었습니다", Toast.LENGTH_SHORT).show();
                        }else{
                            MemberVO vo = new Gson().fromJson(data, MemberVO.class);

                            CommonVal.loginInfo = new Gson().fromJson(data, new TypeToken<MemberVO>(){}.getType());

                            Log.d("로그", "onResult: "+ data);

                            if(vo == null) {
                                Toast.makeText(LoginActivity.this, "vo값이 없습니다", Toast.LENGTH_SHORT).show();
                            }else {
                                CommonVal.loginInfo = vo;
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("vo", vo);
                                startActivity(intent);
                            }
                        }
                    }
                });

            }
        });








    }







    
    
}