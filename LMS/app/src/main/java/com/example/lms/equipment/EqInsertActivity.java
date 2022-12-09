package com.example.lms.equipment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EqInsertActivity extends AppCompatActivity {
    EditText addeqname, addeqquantity, adddate, addinfo, addprice;
    Button eqinsert_btn, eqback_btn ,date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq_insert);
        EquipmentVO vo = new EquipmentVO();
        eqinsert_btn = findViewById(R.id.eqeqinsert_btn);
        eqback_btn = findViewById(R.id.eqback_btn);

        addeqname = findViewById(R.id.addeqname);
        addeqquantity = findViewById(R.id.addeqquantity);
        adddate = findViewById(R.id.adddate);
        addinfo = findViewById(R.id.addinfo);
        addprice = findViewById(R.id.addprice);

        date = findViewById(R.id.insert_date);
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        //--------------------DatePickerDialog-------------------//
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                adddate.setText(year+"/" + (month+1) +"/" +dayOfMonth);
                Calendar c = Calendar.getInstance();
                c.set(year , month , dayOfMonth);
                vo.setBuy_day( new SimpleDateFormat("yyyy/MM/dd").format(new Date(c.getTimeInMillis())) );      //구입날자
                //vo.setBuy_day(new Date(c.getTimeInMillis()));      //구입날자

            }
        }, mYear, mMonth, mDay);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.isClickable()){
                    datePickerDialog.show();
                }
            }
        });





        //뒤로가기 버튼
        eqback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //insert 버튼
        eqinsert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Log.d("태그", "onClick: 버튼 눌렀음.");
                try {
                    vo.setEquipment_num(Integer.parseInt(addeqquantity.getText()+""));
                    vo.setPrice(Integer.parseInt(addprice.getText()+""));
                }catch (Exception e){
                    Log.d("로그", "onClick: insert 오류");
                }
                vo.setEquipment(addeqname.getText()+"");
                vo.setSituation(addinfo.getText()+"");

                CommonAskTask task = new CommonAskTask("andeqinsert",EqInsertActivity.this);
                task.addParam("vo",new Gson().toJson(vo));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult && data.equals("1")){
                            Log.d("태그", "zonResult: 실패");
                            finish();
                        }else{
                            Log.d("태그", "onResult: 실패");
                            finish();
                            //Toast.makeText(EqInsertActivity.this,"실패",Toast.LENGTH_SHORT).show();
                        }
                        //return null;
                    }
                });
            }
        });

    }
}