package com.example.lms.lecture;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonVal;
import com.example.lms.member.MemberVO;

import java.util.ArrayList;

public class LectureMyAdapter extends RecyclerView.Adapter<LectureMyAdapter.MyHolder> {
    LayoutInflater inflater;
    ArrayList<LectureVO> list;
    MainActivity activity;

    MemberVO vo;

    public LectureMyAdapter(LayoutInflater inflater, ArrayList<LectureVO> list, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = (MainActivity) activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_lec_tea, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder h, int i) {
        h.lecture_title.setText(list.get(i).getLecture_title());
        h.teacher_name.setText(list.get(i).getTeacher_name());
        h.lecture_room.setText(list.get(i).getLecture_room());
        h.sortation.setText(list.get(i).getSortation());
        h.lecture_num.setText(list.get(i).getLecture_num() + "");
        final int index = i;

        if (CommonVal.loginInfo.getInfo_cd() == 3) {
        h.lec_tea_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(h.lecture_num.getContext(), Lecture_Tea_DetailActivity.class);
                intent.putExtra("vo", list.get(index));
                h.lecture_num.getContext().startActivity(intent);

            }
        });
        }else {

        }






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView lecture_num, lecture_title, teacher_name, lecture_room, sortation;
        CardView lec_tea_stu;

        public MyHolder(@NonNull View v) {
            super(v);
            sortation = v.findViewById(R.id.sortation);
            lecture_room = v.findViewById(R.id.lecture_room);
            teacher_name = v.findViewById(R.id.teacher_name);
            lecture_title = v.findViewById(R.id.lecture_title);
            lec_tea_stu = v.findViewById(R.id.lec_tea_stu);
            lecture_num = v.findViewById(R.id.lecture_num);



        }
    }
}
