package com.example.lms.lms;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASEURL = "http://112.164.58.181:3301/lmsand/";
    private static Retrofit retrofit;
    public static Retrofit getApiClient(){
        if(retrofit == null) { // 최초로 Retrofit객체를 사용할때 초기화가 안되어있다면 그때 한번만 초기화되게 함.
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)// 스프링 미들웨어의 홈 주소를 의미함
                    .addConverterFactory(ScalarsConverterFactory.create())// json String형태 사용가능하게 함.
                    .client(new OkHttpClient.Builder().readTimeout(10 , TimeUnit.SECONDS).build())
                    // 10초안에 응답이 안오면 통신 연결시도를 종료하고 실패처리를 한다.↑
                    .build();

        }
        return retrofit;
    }






}
