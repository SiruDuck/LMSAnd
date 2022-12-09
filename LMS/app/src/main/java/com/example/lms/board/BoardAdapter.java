package com.example.lms.board;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.example.lms.notice.NoticeAdapter;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.RecHolder>{
    LayoutInflater inflater;
    ArrayList<BoardVO> list;
    Context context;

    public BoardAdapter(LayoutInflater inflater, ArrayList<BoardVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_board_list, parent, false);
        return new RecHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder h, @SuppressLint("RecyclerView") int i) {
        h.board_title.setText(list.get(i).getTitle());
        h.board_time.setText(list.get(i).getWritedate().substring(0, list.get(i).getWritedate().indexOf(" ")));
        h.board_writer.setText(list.get(i).getName());
        h.board_content.setText(list.get(i).getContent());

       if(list.get(i).getFilepath()==null){
            Glide.with(context).load(list.get(i).getFilepath()).into(h.board_imgfile);
        }

       final int index = i;

       h.board_cardview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(h.board_title.getContext(), BoardDetailActivity.class);
               intent.putExtra("vo", list.get(index));
               h.board_title.getContext().startActivity(intent);
               CommonAskTask askTask = new CommonAskTask("andBolist",context);
               askTask.addParam("id",list.get(i).getId());
               askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                   @Override
                   public void onResult(String data, boolean isResult) {
                       Log.d("TAG", "onResult: "+data);
                   }
               });



           }
       });


    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecHolder extends RecyclerView.ViewHolder {
        ImageView board_imgfile;
        TextView board_title, board_time, board_writer, board_content;
        CardView board_cardview;
        public RecHolder(@NonNull View v) {
            super(v);
            board_imgfile = v.findViewById(R.id.board_imgfile);
            board_title = v.findViewById(R.id.board_title);
            board_time = v.findViewById(R.id.board_time);
            board_writer = v.findViewById(R.id.board_writer);
            board_content = v.findViewById(R.id.board_content);


            board_cardview = v.findViewById(R.id.board_cardview);

        }
    }






}
