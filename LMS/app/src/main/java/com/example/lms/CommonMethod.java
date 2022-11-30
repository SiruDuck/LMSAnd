package com.example.lms;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethod {

    public static String dateToString(Date date){
        Log.d("TAG", "dateToString: "  + date.toString()) ;
        Log.d("TAG", "dateToString: "  + date.getYear()) ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //원하는 데이터 포맷 지정
        String strNowDate = simpleDateFormat.format(date);
        return strNowDate ;
    }
}
