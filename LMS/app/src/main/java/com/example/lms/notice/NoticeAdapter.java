package com.example.lms.notice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<NoticeVO> list;
    Context context;
    MainActivity activity;

    public NoticeAdapter(LayoutInflater inflater, ArrayList<NoticeVO> list, Context context, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.activity = (MainActivity) activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.item_notice_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, @SuppressLint("RecyclerView") int i) {

        h.notice_title.setText(list.get(i).getTitle());
        h.notice_time.setText(list.get(i).getWritedate().substring(0, list.get(i).getWritedate().indexOf(" ")));
        h.notice_content.setText(list.get(i).getContent());
        if(list.get(i).getFilepath()==null){
            Glide.with(context).load(list.get(i).getFilepath()).into(h.notice_imgfile);
        }
        final  int index = i;

        h.notice_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(h.notice_title.getContext(), NoticeDetailActivity.class);
                intent.putExtra("isEnable", false);
                intent.putExtra("vo", list.get(index));
                h.notice_title.getContext().startActivity(intent);
                CommonAskTask askTask = new CommonAskTask("andNolist",context);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView notice_imgfile;
        TextView notice_title, notice_time, notice_content;
        CardView notice_cardview;
        public ViewHolder(@NonNull View v) {
            super(v);
            notice_imgfile = v.findViewById(R.id.notice_imgfile);
            notice_title = v.findViewById(R.id.notice_title);
            notice_time = v.findViewById(R.id.notice_time);
            notice_content = v.findViewById(R.id.notice_content);


            notice_cardview = v.findViewById(R.id.notice_cardview);
        }
    }


}
