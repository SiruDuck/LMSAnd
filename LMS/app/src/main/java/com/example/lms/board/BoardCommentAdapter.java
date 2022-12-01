package com.example.lms.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;

import java.util.ArrayList;

public class BoardCommentAdapter extends RecyclerView.Adapter<BoardCommentAdapter.RecHolder>{
    LayoutInflater inflater;
    ArrayList<BoardCommentVO> list;
    Context context;

    public BoardCommentAdapter(LayoutInflater inflater, ArrayList<BoardCommentVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_board_comment, parent, false);


        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder {
        TextView board_comment_id, board_comment_date, board_comment_content;
        public RecHolder(@NonNull View v) {
            super(v);
            board_comment_id = v.findViewById(R.id.board_comment_id);
            board_comment_date = v.findViewById(R.id.board_comment_date);
            board_comment_content = v.findViewById(R.id.board_comment_content);

        }
    }




}
