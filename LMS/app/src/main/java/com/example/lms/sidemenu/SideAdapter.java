package com.example.lms.sidemenu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import com.example.lms.R;

import java.util.ArrayList;

public class SideAdapter extends BaseExpandableListAdapter {
    //Typeface font = ResourcesCompat.getFont(convertView.getContext(), R.font.welcomb);

    //어댑터에서는 붙일수있는 칸을 제공함.
    //실제로 칸에 어떤 Layout을 붙이는 처리는 개발자(LayoutInflater)
    LayoutInflater inflater;
    ArrayList<SideVO> list;
    FragmentManager manager;

    public SideAdapter(LayoutInflater inflater, ArrayList<SideVO> list, FragmentManager manager) {
        this.inflater = inflater;
        this.list = list;
        this.manager = manager;
    }




    //부모 아이템 갯수
    @Override
    public int getGroupCount() {
        return list.size();
    }

    //자식 아이템 갯수 //Object Field <List>
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).list.size();
    }

    //부모 Object
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    //자식 Object
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  list.get(groupPosition).list.get(childPosition);
    }

    //groupPosition 그대로 return
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //groupPosition 그대로 return
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //동일한 아이디가 항상 동일한 개체를 참조하는지 여부
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //부모뷰에 아이템을 붙이는 처리
    //(ViewHolder를 강제 x)
    @Override
    public View getGroupView(int i, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder h = null;
        if(convertView == null ){

            convertView = inflater.inflate(R.layout.item_side_main, parent, false);
            h = new GroupViewHolder(convertView);
            convertView.setTag(h);
        }else{
             h = (GroupViewHolder) convertView.getTag();
        }

        if(list.get(i).getList().size() == 0){
            h.imgv_arrow.setVisibility(View.GONE);
        }
        h.imgv_logo.setImageResource(list.get(i).getImageId());
        h.view_line.setBackgroundColor(Color.parseColor(list.get(i).getMainColor()));
        h.tv_menu_sub.setText(list.get(i).getSubTitle());

        if(isExpanded){
            h.imgv_arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            h.tv_menu.setTypeface(h.tv_menu.getTypeface(), Typeface.BOLD);
            h.tv_menu.setTextSize(17);
            //h.tv_menu.setTypeface(font);
            h.ln_layout.setBackgroundColor(Color.parseColor("#ECECF3"));
        }else{
            h.imgv_arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            h.tv_menu.setTypeface(null, Typeface.NORMAL);
            h.tv_menu.setTextSize(17);
            h.ln_layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        h.tv_menu.setText(list.get(i).getMenu());

        return convertView;
    }


    public class GroupViewHolder{
        ImageView imgv_logo , imgv_arrow;
        TextView tv_menu  , tv_menu_sub;
        LinearLayout ln_layout;
        View view_line;


        public GroupViewHolder(View v) {
            this.imgv_logo = v.findViewById(R.id.imgv_logo);
            this.imgv_arrow =v.findViewById(R.id.imgv_arrow);
            this.tv_menu =v.findViewById(R.id.tv_menu);
            this.ln_layout =v.findViewById(R.id.ln_layout);
            this.tv_menu_sub =v.findViewById(R.id.tv_menu_sub);
            this.view_line =v.findViewById(R.id.view_line);
        }
    }
    //자식뷰에 아이템을 붙이는 처리
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.item_side_sub, parent, false);
        if(list.get(groupPosition).list.size() > 0){
            TextView side_sub =  v.findViewById(R.id.tv_sub_menu);
            //부모 포지션 안쪽 => 자식 포지션 0~size();

            side_sub.setText(list.get(groupPosition).list.get(childPosition).getMenu());



        }

        //manager.beginTransaction().replace(R.id.container, new LectureFragment()).commit();
        return v;
    }

    //자식 아이템 선택되는지
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
