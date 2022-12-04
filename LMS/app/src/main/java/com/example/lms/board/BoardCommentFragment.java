package com.example.lms.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.MainActivity;
import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.notice.NoticeAdapter;
import com.example.lms.notice.NoticeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BoardCommentFragment extends Fragment {
    RecyclerView comment_recv;
    String id;
    public BoardCommentFragment(String id) {
        this.id= id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board_comment, container, false);
        comment_recv = v.findViewById(R.id.comment_recv);







        board_pop_select();



        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        board_pop_select();
    }

    public void board_pop_select() {
        CommonAskTask askTask = new CommonAskTask("comment" , getContext());

        askTask.addParam("board_id", id);
        askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("공지사항", "onResult: " + data);
                if (isResult) {
                    ArrayList<BoardCommentVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<BoardCommentVO>>() {
                            }.getType());
                    Log.d("공지사항", "onResult: " + list.size());

                    BoardCommentAdapter adapter = new BoardCommentAdapter(getLayoutInflater(), list ,getContext(), BoardCommentFragment.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext() , RecyclerView.VERTICAL , false
                    );

                    comment_recv.setAdapter(adapter);
                    comment_recv.setLayoutManager(manager);
                }
            }
        });

    }





}