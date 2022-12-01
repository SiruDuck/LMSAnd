package com.example.lms.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BoardFragment extends Fragment {
    RecyclerView board_recv, board_pop_recv;
    FloatingActionButton board_btn_add;
    BoardVO vo = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);
        board_recv = v.findViewById(R.id.board_recv);
        board_pop_recv = v.findViewById(R.id.board_pop_recv);
        board_btn_add = v.findViewById(R.id.board_btn_add);






        board_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardInsertActivity.class);
                intent.putExtra("vo", vo);
                startActivity(intent);
            }
        });


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        board_select();
        board_pop_select();
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
                //return null;
            }
        });

    }

    public void board_pop_select() {
        CommonAskTask askTask = new CommonAskTask("bopoplist" , getContext());
        askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("writedate", "onResult: " + data);
                if (isResult) {
                    ArrayList<BoardVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>() {
                            }.getType());
                    Log.d("자유게시판", "onResult: " + list.size());

                    BoardPopAdapter adapter = new BoardPopAdapter(getLayoutInflater(), list, getContext());
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext() , RecyclerView.HORIZONTAL , false
                    );

                    board_pop_recv.setAdapter(adapter);
                    board_pop_recv.setLayoutManager(manager);

                }
            }
        });



    }


}