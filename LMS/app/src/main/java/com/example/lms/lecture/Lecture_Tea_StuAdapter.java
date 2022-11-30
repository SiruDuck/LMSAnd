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
import com.example.lms.member.MemberVO;

import java.util.ArrayList;

public class Lecture_Tea_StuAdapter extends RecyclerView.Adapter<Lecture_Tea_StuAdapter.Tea_StuHolder> {
    LayoutInflater inflater;
    ArrayList<LectureVO> list;
    MainActivity activity;

    MemberVO vo;

    public Lecture_Tea_StuAdapter(LayoutInflater inflater, ArrayList<LectureVO> list, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.activity = (MainActivity) activity;
    }

    @NonNull
    @Override
    public Tea_StuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_stu_info, parent, false);
        return new Tea_StuHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Tea_StuHolder h, int i) {
       // h.lecture_num.setText(list.get(i).getLecture_num()+"");

        h.id.setText(list.get(i).getId());
        h.name.setText(list.get(i).getName());
        h.gender.setText(list.get(i).getGender());
        h.grade.setText(list.get(i).getGrade());
        h.department.setText(list.get(i).getDepartment_name());
        h.phone.setText(list.get(i).getPhone());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Tea_StuHolder extends RecyclerView.ViewHolder {
        TextView  id, name, gender, grade, department, phone;
        CardView lec_tea_stu;

        public Tea_StuHolder(@NonNull View v) {
            super(v);
           // lecture_num = v.findViewById(R.id.lecture_num);
            id = v.findViewById(R.id.id);
            name = v.findViewById(R.id.name);
            gender = v.findViewById(R.id.gender);
            grade = v.findViewById(R.id.grade);
            department = v.findViewById(R.id.department);
            phone = v.findViewById(R.id.phone);
            lec_tea_stu = v.findViewById(R.id.lec_tea_stu);

        }
    }
}
