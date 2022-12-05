package com.example.lms.lecture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.MainActivity;
import com.example.lms.R;

import java.util.ArrayList;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.LecHolder> {
    LayoutInflater inflater;
    ArrayList<LectureVO> list;
    MainActivity activity;



    public LectureAdapter(LayoutInflater inflater, ArrayList<LectureVO> list, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = (MainActivity) activity;
    }


    @NonNull
    @Override
    public LecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_lecture, parent, false);
        return new LecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LecHolder h, @SuppressLint("RecyclerView") int i) {


        h.lecture_title.setText(list.get(i).getLecture_title());
        h.teacher_name.setText(list.get(i).getTeacher_name());
        h.lecture_room.setText(list.get(i).getLecture_room());
        h.sortation.setText(list.get(i).getSortation());

        if (list.get(i).getSemester().equals("2")){
            h.view_line.setBackgroundResource(R.color.pink);
        }else{
            h.view_line.setBackgroundResource(R.color.black);
        }

        h.lec_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new LectureDetailFragment(list.get(i).getLecture_num())).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LecHolder extends RecyclerView.ViewHolder {
        CardView lec_detail;
        TextView lecture_num, lecture_room, teacher_name, lecture_title, lecture_year, semester,
                subjectcredit, book, lecture_day, lecture_time, enrolment, capacity, midex, finalex,
                state, sortation, reception_status;
        Button btn_modify, btn_delete, btn_save, btn_cancel;
        View view_line;

        public LecHolder(@NonNull View v) {
            super(v);
            sortation = v.findViewById(R.id.sortation);
            lec_detail = v.findViewById(R.id.lec_detail);
            lecture_room = v.findViewById(R.id.lecture_room);
            teacher_name = v.findViewById(R.id.teacher_name);
            lecture_title = v.findViewById(R.id.lecture_title);
            lecture_year = v.findViewById(R.id.lecture_year);
            semester = v.findViewById(R.id.semester);
//            reception_status = v.findViewById(R.id.reception_status);
            btn_modify = v.findViewById(R.id.btn_modify);
            btn_delete = v.findViewById(R.id.btn_delete);
            btn_save = v.findViewById(R.id.btn_save);
            view_line = v.findViewById(R.id.view_line);

        }
    }
}
