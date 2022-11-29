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

public class BoardFragment extends Fragment {
    RecyclerView board_recv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);
        board_recv = v.findViewById(R.id.board_recv);

        board_select();
        return v;
    }


    @Override
    public void onResume() {
        board_select();
        super.onResume();
    }


    public void board_select() {
        CommonAskTask askTask = new CommonAskTask("bolist" , getContext());
        askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("자유게시판", "onResult: " + data);
                if (isResult) {
                    ArrayList<BoardVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>() {
                            }.getType());
                    Log.d("자유게시판", "onResult: " + list.size());

                    BoardAdapter adapter = new BoardAdapter(getLayoutInflater(), list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext() , RecyclerView.VERTICAL , false
                    );

                    board_recv.setAdapter(adapter);
                    board_recv.setLayoutManager(manager);

                }
            }
        });

    }



}