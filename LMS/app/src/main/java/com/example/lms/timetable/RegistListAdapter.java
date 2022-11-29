package com.example.lms.timetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.MotionButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;

import java.util.ArrayList;

public class RegistListAdapter extends RecyclerView.Adapter<RegistListAdapter.ViewHolder>{
    private final RegistListFragment registListFragment;
    LayoutInflater inflater;
    ArrayList<TimeTableVO> table_vo;

    public RegistListAdapter(LayoutInflater inflater, ArrayList<TimeTableVO> table_vo, RegistListFragment registListFragment) {
        this.inflater = inflater;
        this.table_vo = table_vo;
        this.registListFragment = registListFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_regist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lecture_num.setText(table_vo.get(position).getLecture_num()+"");
        holder.lecture_title.setText(table_vo.get(position).getLecture_title());
        holder.lecture_room.setText(table_vo.get(position).getLecture_room());
        holder.teacher_name.setText(table_vo.get(position).getTeacher_name());
        holder.lecture_time.setText(table_vo.get(position).getLecture_day() + "요일 (" + table_vo.get(position).getLecture_time() + "교시)");

        final int index = position;
        holder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.lecture_num.getContext(), TimeTableDetailActivity.class);
                intent.putExtra("list", table_vo.get(index));
                holder.lecture_num.getContext().startActivity(intent);
            }
        });

        holder.btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnrolmentVO enrol_vo = new EnrolmentVO();
                CommonAskTask task = new CommonAskTask("delete.at", holder.btn_regist.getContext());
                task.addParam("lecture_num", enrol_vo.getLecture_num());
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        registListFragment.list_select();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return table_vo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lecture_num, lecture_title, lecture_room, teacher_name, lecture_time;
        MotionButton btn_detail, btn_regist;

        public ViewHolder(@NonNull View v) {
            super(v);
            lecture_num = v.findViewById(R.id.lecture_num);
            lecture_title = v.findViewById(R.id.lecture_title);
            lecture_room = v.findViewById(R.id.lecture_room);
            teacher_name = v.findViewById(R.id.teacher_name);
            lecture_time = v.findViewById(R.id.lecture_time);
            btn_detail = v.findViewById(R.id.btn_detail);
            btn_regist = v.findViewById(R.id.btn_regist);
        }
    }
}
