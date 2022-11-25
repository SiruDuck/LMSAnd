package com.example.lms.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lms.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<NoticeVO> list;
    NoticeFragment fragment;
    Context context;

    public NoticeAdapter(LayoutInflater inflater, ArrayList<NoticeVO> list, NoticeFragment fragment) {
        this.inflater = inflater;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_notice_list, parent , false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
            h.notice_title.setText(list.get(i).getTitle());
            h.notice_time.setText(list.get(i).getWritedate());
            h.notice_content.setText(list.get(i).getContent());
            
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView notice_title, notice_time, notice_content, notice_imgfile;


        public ViewHolder(@NonNull View v) {
            super(v);
            notice_title = v.findViewById(R.id.notice_title);
            notice_time = v.findViewById(R.id.notice_time);
            notice_content = v.findViewById(R.id.notice_content);
            notice_imgfile = v.findViewById(R.id.notice_imgfile);

        }
    }






}
