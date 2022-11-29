package com.example.lms.lecture;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.member.MemberVO;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class LectureMyAdapter extends RecyclerView.Adapter<LectureMyAdapter.MyHolder> {
    LayoutInflater inflater;
    ArrayList<LectureVO> list;
    MainActivity activity;

    public LectureMyAdapter(LayoutInflater inflater, ArrayList<LectureVO> list, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = (MainActivity) activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_my_lec, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder h, int i) {
        h.lecture_title.setText(list.get(i).getLecture_title());
        h.teacher_name.setText(list.get(i).getTeacher_name());
        h.lecture_room.setText(list.get(i).getLecture_room());
        h.sortation.setText(list.get(i).getSortation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView lecture_title, teacher_name, lecture_room, sortation;

        public MyHolder(@NonNull View v) {
            super(v);
            sortation = v.findViewById(R.id.sortation);
            lecture_room = v.findViewById(R.id.lecture_room);
            teacher_name = v.findViewById(R.id.teacher_name);
            lecture_title = v.findViewById(R.id.lecture_title);

        }
    }
}
