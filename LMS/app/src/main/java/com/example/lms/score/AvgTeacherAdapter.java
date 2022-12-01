package com.example.lms.score;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;

import java.util.ArrayList;

public class AvgTeacherAdapter extends RecyclerView.Adapter<AvgTeacherAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<ScoreVO> list;
    Context context;
    SearchView searchView;

    public AvgTeacherAdapter(LayoutInflater inflater, ArrayList<ScoreVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_subject_avg, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, @SuppressLint("RecyclerView") int i) {
        h.subject_title.setText(list.get(i).getLecture_title() + "(" + list.get(i).getLecture_num() + ") : ");
        h.avg.setText(list.get(i).getAvg_subject() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView subject_title, avg;
        public ViewHolder(@NonNull View v) {
            super(v);
            subject_title = v.findViewById(R.id.subjcet_title);
            avg = v.findViewById(R.id.avg);

        }
    }

}
