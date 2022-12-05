package com.example.lms.equipment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lms.R;
import com.example.lms.lms.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EquipmentFragment extends Fragment {
    RecyclerView eq_recv;
    Button eqinsert_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_equipment, container, false);
        eq_recv = v.findViewById(R.id.equipment_list);
        ArrayList<EquipmentVO> list;
        eqinsert_btn = v.findViewById(R.id.eqinsert_btn);
        cus_select();

        eqinsert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EqInsertActivity.class);
                startActivity(intent);
            }
        });



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        cus_select();
    }

    public void cus_select(){
        CommonAskTask task = new CommonAskTask("andeqlist", getContext());
        task.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                //통신 완료 시 데이터를 가지고 온다.
               // Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                if(isResult){
                    ArrayList<EquipmentVO> list =
                            new Gson().fromJson(data, new TypeToken<ArrayList<EquipmentVO>>(){}.getType());

                   EquipmentAdapter adapter = new EquipmentAdapter(getLayoutInflater(),list,EquipmentFragment.this);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(
                            getContext(),RecyclerView.VERTICAL,false
                    );
                    eq_recv.setAdapter(adapter);
                    eq_recv.setLayoutManager(manager);

                }else{
                    Log.d("로그", "onResult:"+data);
                }
                //return null;
            }
        });


    }
}