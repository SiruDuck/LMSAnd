package com.example.lms.notice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lms.R;

public class NoticeDetailFragment extends Fragment {
    TextView notice_detail_title, notice_detail_writer, notice_detail_readcnt, notice_detail_writedate, notice_detail_content, notice_detail_filename;
    ImageView notice_detail_filepath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notice_detail, container, false);


        notice_detail_title = v.findViewById(R.id.notice_detail_title);
        notice_detail_writer = v.findViewById(R.id.notice_detail_writer);
        notice_detail_readcnt = v.findViewById(R.id.notice_detail_readcnt);
        notice_detail_writedate = v.findViewById(R.id.notice_detail_writedate);
        notice_detail_content = v.findViewById(R.id.notice_detail_content);
        notice_detail_filename = v.findViewById(R.id.notice_detail_filename);
        notice_detail_filepath = v.findViewById(R.id.notice_detail_filepath);


















        return v;
    }
}