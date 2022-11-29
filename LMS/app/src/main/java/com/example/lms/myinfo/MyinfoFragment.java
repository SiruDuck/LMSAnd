package com.example.lms.myinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lms.R;


public class MyinfoFragment extends Fragment {
    TextView info_id,info_name, info_department_name, info_grade, info_state, info_start_date, info_phone, info_email, info_addr, info_post, start_date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, container, false);















        return v;
    }
}