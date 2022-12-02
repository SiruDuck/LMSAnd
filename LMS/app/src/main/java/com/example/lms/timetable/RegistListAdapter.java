package com.example.lms.timetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.lms.lms.CommonVal.loginInfo;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.MotionButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.CommonMethod;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;

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

        holder.btn_regist.setVisibility(table_vo.get(position).getLecture_apply()==1 ? View.INVISIBLE : View.VISIBLE);
        holder.btn_delete.setVisibility(table_vo.get(position).getLecture_apply()==1 ? View.VISIBLE : View.INVISIBLE);

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
                CommonAskTask task = new CommonAskTask("insert.at", holder.btn_regist.getContext());
                //2022/11/29 : CommonVal에 Logininfo.getId로 수정해야함
                task.addParam("id", loginInfo.getId());
                task.addParam("lecture_num", table_vo.get(index).getLecture_num());

                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(isResult&&data.equals("1")){
                            Log.d("lms", "onResult: 등록" + data);
                            table_vo.get(index).setLecture_apply(1);
                            holder.btn_regist.setVisibility(View.INVISIBLE);
                            holder.btn_delete.setVisibility(View.VISIBLE);

                        }else{
                            Log.d("lms", "onResult: 등록실패" + data);
                        }
                    }
                });
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lms", "onClick: 삭제");
                CommonAskTask task_d = new CommonAskTask("delete.at", holder.btn_delete.getContext());
                task_d.addParam("id", loginInfo.getId());
                task_d.addParam("lecture_num", table_vo.get(index).getLecture_num());
                task_d.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        Log.d("lms", "onResult: 삭제" + data);
                        table_vo.get(index).setLecture_apply(0);
                        holder.btn_regist.setVisibility(View.VISIBLE);
                        holder.btn_delete.setVisibility(View.INVISIBLE);
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
        MotionButton btn_detail, btn_regist, btn_delete;

        public ViewHolder(@NonNull View v) {
            super(v);
            lecture_num = v.findViewById(R.id.lecture_num);
            lecture_title = v.findViewById(R.id.lecture_title);
            lecture_room = v.findViewById(R.id.lecture_room);
            teacher_name = v.findViewById(R.id.teacher_name);
            lecture_time = v.findViewById(R.id.lecture_time);
            btn_detail = v.findViewById(R.id.btn_detail);
            btn_regist = v.findViewById(R.id.btn_regist);
            btn_delete = v.findViewById(R.id.btn_delete);
        }
    }
}
