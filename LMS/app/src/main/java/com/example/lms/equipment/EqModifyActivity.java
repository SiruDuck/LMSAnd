package com.example.lms.equipment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.lms.CommonMethod;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

public class EqModifyActivity extends AppCompatActivity {
    EditText modieqname,modieqquantity,modiinfo,modiprice;
    TextView modidate;
    Button date , eqmodify_btn, eqdelete_btn, eqback_btn;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq_modify);
        Intent intent = getIntent();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //---------------------찾기--------------------//
        eqmodify_btn = findViewById(R.id.eqmodify_btn); eqdelete_btn = findViewById(R.id.eqdelete_btn) ; eqback_btn = findViewById(R.id.eqback_btn);
        modieqname = findViewById(R.id.modieqname); modieqquantity = findViewById(R.id.modieqquantity); modiinfo = findViewById(R.id.modiinfo); modiprice = findViewById(R.id.modiprice);
        modidate = findViewById(R.id.modidate);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        EquipmentVO vo = (EquipmentVO) intent.getSerializableExtra("vo");
        //-----------Data set--------//
        modieqname.setText(vo.getEquipment()+"");
        modieqquantity.setText(vo.getEquipment_num()+"");
        modiinfo.setText(vo.getSituation());
        modiprice.setText(vo.getPrice()+"");
        modidate.setText( vo.getBuy_day());
        //modidate.setText(  CommonMethod.dateToString(vo.getBuy_day()));



       /* //--------------------DatePickerDialog-------------------//
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                modidate.setText(year+"/" + (month+1) +"/" +dayOfMonth);
                Calendar c = Calendar.getInstance();
                c.set(year , month , dayOfMonth);

                vo.setBuy_day( format.format(new Date(c.getTimeInMillis())) );     //구입날자
                //vo.setBuy_day(new Date(c.getTimeInMillis()));      //구입날자

            }
        }, mYear, mMonth, mDay);*/

    /*    date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date.isClickable()) {
                    datePickerDialog.show();

                }
            }
        });*/

        //----------------------Btn처리----------------------//

        //modify
        eqmodify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    vo.setEquipment_num(Integer.parseInt(String.valueOf(modieqquantity.getText()))); //수량
                    vo.setPrice(Integer.parseInt(String.valueOf(modiprice.getText())));              //가격


                }catch (Exception e){
                    Toast.makeText(EqModifyActivity.this,"정수로 입력해주세요!",Toast.LENGTH_SHORT).show();
                    modieqquantity.setText("정수로 입력해주세요!");
                    modieqquantity.setText("정수로 입력해주세요!");
                }
                Log.d("로그", "onClick: "+vo);
                CommonAskTask task = new CommonAskTask("andeqmodify",EqModifyActivity.this);
                task.addParam("vo",new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult && data.equals("1")){
                            finish();
                        }else{
                            //Toast.makeText(EqModifyActivity.this,"실패",Toast.LENGTH_SHORT).show();
                        }
                        //return null;
                        finish();
                    }
                });


            }
        });

        eqdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonAskTask task = new CommonAskTask("andeqdelete", EqModifyActivity.this);
                task.addParam("equipment",modieqname.getText());
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult && data.equals("1")){
                            finish();
                        }else{
                            //Toast.makeText(EqModifyActivity.this,"실패",Toast.LENGTH_SHORT).show();
                        }
                        //return null;
                    }
                });
                finish();
            }
        });




        //back
        eqback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}