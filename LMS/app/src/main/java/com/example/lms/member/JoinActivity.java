package com.example.lms.member;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lms.MainActivity;
import com.example.lms.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class JoinActivity extends AppCompatActivity {
    Button departbtn1, departbtn2, login_birthbtn;
    TextView login_birthtxt;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
/*

        departbtn1 = findViewById(R.id.departbtn1);

        login_birthbtn = findViewById(R.id.login_birthbtn);
        login_birthtxt = findViewById(R.id.login_birthtxt);

        login_birthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR);
                int pMonth = calendar.get(Calendar.MONTH);
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(JoinActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year + "/" + month + "/" + day;

                        login_birthtxt.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog.show();

            }
        });







*/



    }









}