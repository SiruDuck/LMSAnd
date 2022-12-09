package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lms.member.LoginActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splash = findViewById(R.id.splash);
        Glide.with(this).load(R.drawable.splash).into(splash);
        //new Handler().postDelayed()
        //-내가 사용하려는 메소드의 파라메터가 Interface 타입인 경우
        //1.바로 구현부를 작성하는 방법.
        //2. implements로 상속 후 작성하는 방법
        Log.d("로그", "3초");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //dialog.dismiss();//show 반대 : 안보이게 하는 처리
                //메인액티비티로 화면 이동해보기.
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();//현재 액티비티를 닫는 처리(종료)
            }
        }, 3000);

    }

}