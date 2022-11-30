package com.example.lms.board;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;

import java.util.ArrayList;

public class BoardPopAdapter extends RecyclerView.Adapter<BoardPopAdapter.RecHolder>{
    LayoutInflater inflater;
    ArrayList<BoardVO> list;
    Context context;

    public BoardPopAdapter(LayoutInflater inflater, ArrayList<BoardVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_board_pop_list, parent, false);
        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder h, @SuppressLint("RecyclerView") int i) {
        h.board_pop_title.setText(list.get(i).getTitle());
        h.board_pop_time.setText(list.get(i).getWritedate());
        h.board_pop_writer.setText(list.get(i).getWriter());
        h.board_pop_content.setText(list.get(i).getContent());

        if(list.get(i).getFilepath() == null){
            Glide.with(context).load(list.get(i).getFilepath()).into(h.board_pop_imgfile);
        }

        final int index = i;




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder {
        ImageView board_pop_imgfile;
        TextView board_pop_title, board_pop_time, board_pop_content, board_pop_writer;

        public RecHolder(@NonNull View v) {
            super(v);
            board_pop_imgfile = v.findViewById(R.id.board_pop_imgfile);
            board_pop_title = v.findViewById(R.id.board_pop_title);
            board_pop_time = v.findViewById(R.id.board_pop_time);
            board_pop_content = v.findViewById(R.id.board_pop_content);
            board_pop_writer = v.findViewById(R.id.board_pop_writer);
        }
    }










}
