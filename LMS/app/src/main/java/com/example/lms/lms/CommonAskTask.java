package com.example.lms.lms;

//프로그램은 크게 두 가지의 작업 영역을 가진다. → 개발자
//FRONT(View) : 사람의 눈에 보이는 영역
//BACKGROUND(Back) : 사람의 눈에 보이지 않지만 실제로 데이터가 이동하거나 하는 모든 처리.
//메인 쓰레드 : 사용자가 조작하는 영역의 작업 공간.
//→ 네트워크에 접속해서(스프링) 데이터를 가져와야 하는데 여러가지 이유로 응답이 없을때..
//→ 사용자가 앱을 사용하면서 비동기로 작업이 되어야 할때 등등.
//BackGround Task : 디자인은 안멈춘 상태에서 어떤 작업을 비동기로 하는 것.


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

public class CommonAskTask extends AsyncTask <String, String, String>{
    String url;
    HashMap<String,Object>  params;
    Context context;
    ProgressDialog dialog;
    public AsynckTaskCallback callback;




    public CommonAskTask(String url, Context context) {
        this.url = url;//spring에 있는 여러 mapping에 접근.
        this.context = context;//ProgressDialog 보여주려고
        this.params = new HashMap<>();
        this.dialog = new ProgressDialog(context);
    }

    public void addParam(String key, Object value){
        this.params.put(key, value);
    }

    public void executeAsk(AsynckTaskCallback callback) {
        this.callback = callback;
        this.execute();
    }


    @Override
    protected void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("데이터 처리");
        dialog.setMessage("스프링으로 데이터 가져오는 중");
        dialog.setCancelable(false);
        //dialog.show();//← 실제 다이얼로그가 보이게 만든다
    }





    //안드로이드에서 화면에 안보이는 영역에서 할 작업을 미리 넣어 둠..
    //excute라는 명령에 의해서 실행되는분
    @Override
    protected String doInBackground(String... strings) {
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //enque ← 작업을 비동기로 자동으로 해줌. 불필요한 메소드 구간이 많이 생김.


        try {
            //재사용을 하기 위해서는 url과 param이 고정 되면 안됨.
            //list.hr 조회하기 위해서 파라메터 사원코드 정보를 파라메터로 씀.
            //list.cu 조회해야 함. ↓ 바뀌어야하는 파라메터를 사용하는 곳으로부터 받아옴.

            rtnData = apiInterface.getData(url,params).execute().body();
            Log.d("로그", "doInBackground: " + rtnData);


            //ContentActivity에서 CommonAskTask를 실행했을때 null이나 오류가 아니라
        } catch (IOException e) {
            Log.d ("로그", "CommonAskTask: apiInterface 오류: " + e.getMessage());
            e.printStackTrace();
        }


        return rtnData;
    }

    //excute명령이 전부 끝나고 나서 실행됨.↓
    @Override
    protected void onPostExecute(String rtnData) {
        if (dialog != null) dialog.dismiss();
        //&& 두가지 조건이 모두 true일때 실행{}
        //|| 둘중에 하나라도. true일때 실행{}
        //true || false → 실행
        //1 + 0 → 1 → true

        //true && flase → 실행 x
        //1 * 0 = 0 → false
        // 논리합, 논리곱
            if(rtnData != null && rtnData.length()>0){
                callback.onResult(rtnData, true);
            }else {
                callback.onResult(rtnData,false);
            }
        Log.d("로그", "onPostExecute: " + rtnData);


    }

    //콜백을 위한 인터페이스 정의.
    public interface AsynckTaskCallback{
        void onResult(String data, boolean isResult);
    }

}
