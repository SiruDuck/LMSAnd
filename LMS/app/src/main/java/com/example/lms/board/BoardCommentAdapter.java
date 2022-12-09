package com.example.lms.board;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BoardCommentAdapter extends RecyclerView.Adapter<BoardCommentAdapter.RecHolder>{
    LayoutInflater inflater;
    ArrayList<BoardCommentVO> list;
    Context context;
    BoardCommentFragment fragment;

    public BoardCommentAdapter(LayoutInflater inflater, ArrayList<BoardCommentVO> list, Context context, BoardCommentFragment fragment) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_board_comment, parent, false);



        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder h, int i) {
        h.board_comment_id.setText(list.get(i).getWriter());
        h.board_comment_date.setText(list.get(i).getWritedate().substring(0, list.get(i).getWritedate().indexOf(" ")));
        h.board_comment_content.setText(list.get(i).getContent());
        final int index = i;

        if(CommonVal.loginInfo.getName().equals(list.get(index).getWriter())){
            h.board_comment_delete.setVisibility(View.VISIBLE);
            h.board_comment_modi.setVisibility(View.VISIBLE);
        }else{
            h.board_comment_delete.setVisibility(View.GONE);
            h.board_comment_modi.setVisibility(View.GONE);
        }



        h.board_comment_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("andcomment.delete", h.board_comment_id.getContext());
                task.addParam("id", list.get(index).getId());
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        fragment.board_pop_select();

                    }
                });
            }
        });

        h.board_comment_modi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               h.edt_comment_content.setVisibility(View.VISIBLE);
               h.board_comment_content.setVisibility(View.GONE);
               h.board_comment_modi.setVisibility(View.GONE);
               h.board_comment_delete.setVisibility(View.GONE);
               h.comment_btn_modi.setVisibility(View.VISIBLE);
               h.comment_btn_cancel.setVisibility(View.VISIBLE);

               h.edt_comment_content.setText(list.get(index).getContent());


            }
        });

        h.comment_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.edt_comment_content.setVisibility(View.GONE);
                h.comment_btn_modi.setVisibility(View.GONE);
                h.comment_btn_cancel.setVisibility(View.GONE);

                h.board_comment_content.setVisibility(View.VISIBLE);
                h.board_comment_modi.setVisibility(View.VISIBLE);
                h.board_comment_delete.setVisibility(View.VISIBLE);
            }
        });


        h.comment_btn_modi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(index).setContent(h.edt_comment_content.getText()+"");

                CommonAskTask task = new CommonAskTask("andupdate.comment", context);
                task.addParam("vo", new Gson().toJson(list.get(index)));
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult){
                            h.edt_comment_content.setVisibility(View.GONE);
                            h.comment_btn_modi.setVisibility(View.GONE);
                            h.comment_btn_cancel.setVisibility(View.GONE);

                            h.board_comment_content.setVisibility(View.VISIBLE);
                            h.board_comment_modi.setVisibility(View.VISIBLE);
                            h.board_comment_delete.setVisibility(View.VISIBLE);

                            fragment.board_pop_select();
                        }

                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder {
        TextView board_comment_id, board_comment_date, board_comment_content;
        Button board_comment_modi, board_comment_delete, comment_btn_modi, comment_btn_cancel;
        EditText edt_comment_content;
        public RecHolder(@NonNull View v) {
            super(v);
            board_comment_id = v.findViewById(R.id.board_comment_id);
            board_comment_date = v.findViewById(R.id.board_comment_date);
            board_comment_content = v.findViewById(R.id.board_comment_content);
            board_comment_modi = v.findViewById(R.id.board_comment_modi);
            board_comment_delete = v.findViewById(R.id.board_comment_delete);

            edt_comment_content = v.findViewById(R.id.edt_comment_content);
            comment_btn_modi = v.findViewById(R.id.comment_btn_modi);
            comment_btn_cancel = v.findViewById(R.id.comment_btn_cancel);


        }
    }




}
