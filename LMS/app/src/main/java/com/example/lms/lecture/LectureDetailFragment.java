package com.example.lms.lecture;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.lms.CommonVal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class LectureDetailFragment extends Fragment {
    CardView lec_detail;
    List<LectureVO> list;
    Button btn_back, btn_modify, btn_delete;
    LectureVO vo = null;
    TextView lecture_num, lecture_room, teacher_name, lecture_title, lecture_year, semester,
            subjectcredit, book, lecture_day, lecture_time, enrolment, capacity, midex, finalex,
            state, sortation, reception_status;
    LectureFragment fragment;

    int lecture_num1;

    public LectureDetailFragment(int lecture_num) {
        this.lecture_num1 = lecture_num;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_detail, container, false);


        lec_detail = v.findViewById(R.id.lec_detail);

        lecture_num = v.findViewById(R.id.lecture_num);
        lecture_room = v.findViewById(R.id.lecture_room);
        teacher_name = v.findViewById(R.id.teacher_name);
        lecture_title = v.findViewById(R.id.lecture_title);
        lecture_year = v.findViewById(R.id.lecture_year);
        semester = v.findViewById(R.id.semester);
        subjectcredit = v.findViewById(R.id.subjectcredit);
        book = v.findViewById(R.id.book);
        lecture_day = v.findViewById(R.id.lecture_day);
        lecture_time = v.findViewById(R.id.lecture_time);
        //enrolment = v.findViewById(R.id.enrolment);
        capacity = v.findViewById(R.id.capacity);
        midex = v.findViewById(R.id.midex);
        finalex = v.findViewById(R.id.finalex);
        state = v.findViewById(R.id.state);
        sortation = v.findViewById(R.id.sortation);
        //reception_status = v.findViewById(R.id.reception_status);



        btn_modify = v.findViewById(R.id.btn_modify);
        btn_delete = v.findViewById(R.id.btn_delete);
        btn_back = v.findViewById(R.id.btn_back);

        if(CommonVal.loginInfo.getInfo_cd() == 3){
            btn_modify.setVisibility(View.VISIBLE);
            btn_delete.setVisibility(View.GONE);
            btn_back.setVisibility(View.VISIBLE);
        }else{
            btn_modify.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
            btn_back.setVisibility(View.VISIBLE);
        }

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(lecture_num.getContext(), LectureModifyActivity.class);
               intent.putExtra("vo" , vo);
               intent.putExtra("isEnable", false);
               getActivity().startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LectureFragment()).commit();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask task = new CommonAskTask("anddelete.lec", btn_delete.getContext());
                //task.addParam("lecture_num", vo);
                task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        fragment.lecture_list();
                        //return null;
                    }
                });
            }
        });

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        lecture_detail();
    }

    public void lecture_detail(){
        CommonAskTask task = new CommonAskTask("anddetail.lec", getContext());
        task.addParam("lecture_num", lecture_num1);
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                if(isResult){
                    vo = new Gson().fromJson(data, new TypeToken<LectureVO>(){}.getType());

                   lecture_num.setText(vo.getLecture_num()+"");
                   lecture_room.setText(vo.getLecture_room());
                   teacher_name.setText(vo.getTeacher_name());
                   lecture_title.setText(vo.getLecture_title());
                   lecture_year.setText(vo.getLecture_year());
                   semester.setText(vo.getSemester());
                   subjectcredit.setText(vo.getSubjectcredit());
                   book.setText(vo.getBook());
                   lecture_day.setText(vo.getLecture_day());
                   lecture_time.setText(vo.getLecture_time());
                   //enrolment.setText(vo.getEnrolment());
                   capacity.setText(vo.getCapacity()+"");
                   midex.setText(vo.getMidex()+"");
                   finalex.setText(vo.getFinalex()+"");
                   state.setText(vo.getState());
                   sortation.setText(vo.getSortation());
                   //reception_status.setText(vo.getReception_status());



                }else {
                    Log.d("TAG", "onResult: 안됨");
                }
                //eturn null;
            }
        });
    }
}