package com.example.lms.equipment;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.CommonMethod;
import com.example.lms.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
LayoutInflater inflater;
ArrayList<EquipmentVO> list;
EquipmentFragment fragment;

    public EquipmentAdapter(LayoutInflater layoutInflater, ArrayList<EquipmentVO> list, EquipmentFragment equipmentFragment) {
        this.inflater = layoutInflater;
        this.list = list;
        this.fragment = equipmentFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = inflater.inflate(R.layout.item_equipment,parent,false);
        //칸 마다 보여줄 정보 layout
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {

        h.eq_name.setText(list.get(i).getEquipment());
        h.eq_quantity.setText(list.get(i).getEquipment_num()+"");
        h.eq_price.setText(list.get(i).getPrice()+"");
        h.eq_day.setText( list.get(i).getBuy_day() );
        //h.eq_day.setText(CommonMethod.dateToString(list.get(i).getBuy_day()));

        final int index = i;
        // 목록 컬러 변경

        h.eqmodify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //modi 수정화면으로 변경.
                Intent intent = new Intent(h.eq_name.getContext(),EqModifyActivity.class);
                intent.putExtra("vo",list.get(index));
                h.eq_name.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView layout;
        TextView eq_name, eq_quantity, eq_day, eq_price;
        Button eqmodify_btn;

        public ViewHolder(@NonNull View v) {
            super(v);
            eq_name = v.findViewById(R.id.eq_name);
            eq_quantity = v.findViewById(R.id.eq_quantity);
            eq_day = v.findViewById(R.id.eq_day);
            eq_price = v.findViewById(R.id.eq_price);
            eqmodify_btn = v.findViewById(R.id.eqmodify);
            layout = v.findViewById(R.id.background);
        }
    }
}
