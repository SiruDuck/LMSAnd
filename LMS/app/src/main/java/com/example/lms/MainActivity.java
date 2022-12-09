package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lms.board.BoardFragment;
import com.example.lms.equipment.EquipmentFragment;
import com.example.lms.lecture.LectureFragment;
import com.example.lms.lecture.Lecture_StuFragment;
import com.example.lms.lecture.Lecture_TeaFragment;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.member.MemberVO;
import com.example.lms.myinfo.MyinfoFragment;
import com.example.lms.notice.NoticeFragment;
import com.example.lms.score.ScoreFragment;
import com.example.lms.score.ScoreTeacherFragment;
import com.example.lms.sidemenu.SideAdapter;
import com.example.lms.sidemenu.SideVO;
import com.example.lms.timetable.RegistListFragment;
import com.example.lms.timetable.TimeTableFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ExpandableListView expd_listv;
    NavigationView nav_view;
    int currInx = -1 ;
    ArrayList<SideVO> main_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        MemberVO vo = (MemberVO) intent.getSerializableExtra("vo");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        /*
        CommonAskTask askTask = new CommonAskTask("andLogin", this);
        askTask.addParam("id",new Gson().toJson(vo));
        askTask.addParam("pw", new Gson().toJson(vo));

        askTask.executeAsk(new CommonAskTask.AsynckTaskCallback() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("아이디", "onResult: "+ isResult);
                Log.d("비밀번호", "onResult: "+ data);
            }
        });
*/

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close

        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nav_view = findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        TextView tv1 = headerView.findViewById(R.id.loginID);
        TextView tv2 = headerView.findViewById(R.id.loginno);
        CircleImageView loginImage = headerView.findViewById(R.id.loginImage);
        tv1.setText(vo.getName()+"");
        tv2.setText(vo.getId());
        Glide.with(this).load(vo.getProfile()).into(loginImage);
        headerView.findViewById(R.id.imgv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        headerView.findViewById(R.id.imgv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        expd_listv = findViewById(R.id.expd_listv);





        main_list= new ArrayList<>();
        if(vo.getInfo_cd() == 3){
            main_list = getTeacherList();
        }else if(vo.getInfo_cd() == 1){
            main_list = getStudentList();
        }else if(vo.getInfo_cd() == 4){
            main_list = getAdminList();
        }
        //프래그먼트 붙이는처리
        Bundle bundle = new Bundle();
        bundle.putString("Name",vo.getName());
        Log.d("태그", "onCreate: 번들값:"+vo.getName());
        Lecture_TeaFragment  Lecture_TeaFragment = new Lecture_TeaFragment();
        Lecture_TeaFragment.setArguments(bundle);


        SideAdapter adapter = new SideAdapter(getLayoutInflater(), main_list, getSupportFragmentManager());
        expd_listv.setAdapter(adapter);

        expd_listv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(main_list.get(groupPosition).getFragment()!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, main_list.get(groupPosition).getFragment()).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if(groupPosition == i){
                        parent.expandGroup(i);
                        if(groupPosition == currInx){
                            parent.collapseGroup(i);
                            currInx = -1;
                        }else{
                            currInx = groupPosition;
                        }
                    }else{
                        parent.collapseGroup(i);

                    }
                }



                return true;
            }
        });

        expd_listv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(main_list.get(groupPosition).getList().get(childPosition).getFragment()!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, main_list.get(groupPosition).getList().get(childPosition).getFragment()).commit();
                    drawer.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }







    public ArrayList<SideVO>  getTeacherList(){

        ArrayList<SideVO> main_list = new ArrayList<>();

        ArrayList<SideVO> sub_list1 = new ArrayList<>();
        main_list.add(new SideVO("홈으로","" , "#123456"  , sub_list1 , new HomeFragment()));
        main_list.add(new SideVO("내 정보","" , "#123456"  , sub_list1 , new MyinfoFragment()));
        main_list.add(new SideVO("학생 성적 확인", "" , "#661234", sub_list1, new ScoreTeacherFragment() ));
        main_list.get(0).setImageId(R.drawable.menuimg1);
        main_list.get(1).setImageId(R.drawable.menuimg2);


        ArrayList<SideVO> sub_list2 = new ArrayList<>();
        sub_list2.add(new SideVO("전체 강의목록", new LectureFragment()));
        sub_list2.add(new SideVO("내 강의목록", new Lecture_TeaFragment()));
        main_list.add(new SideVO("강의 관리","" , "#654321"  , sub_list2 ));
        main_list.get(2).setImageId(R.drawable.menuimg3);


        main_list.get(3).setImageId(R.drawable.menuimg4);


        ArrayList<SideVO> sub_list4 = new ArrayList<>();
        sub_list4.add(new SideVO("공지사항", new NoticeFragment()));
        sub_list4.add(new SideVO("자유게시판", new BoardFragment()));
        main_list.add(new SideVO("게시판","" , "#661234"  , sub_list4 ));
        main_list.get(4).setImageId(R.drawable.menuimg5);

        return main_list;
    }
    public ArrayList<SideVO>  getStudentList(){

        ArrayList<SideVO> main_list = new ArrayList<>();

        ArrayList<SideVO> sub_list1 = new ArrayList<>();
        main_list.add(new SideVO("홈으로","" , "#ECB365"  , sub_list1 , new HomeFragment()));
        main_list.add(new SideVO("내 정보","" , "#C84B31"  , sub_list1 , new MyinfoFragment()));
        main_list.add(new SideVO("내 성적 확인", "" , "#1E5128", sub_list1, new ScoreFragment() ));
        main_list.get(0).setImageId(R.drawable.menuimg1);     // 홈으로


        ArrayList<SideVO> sub_list2 = new ArrayList<>();
        sub_list2.add(new SideVO("전체 강의목록", new LectureFragment()));
        sub_list2.add(new SideVO("내 강의목록", new Lecture_StuFragment()));
        sub_list2.add(new SideVO("내 시간표", new TimeTableFragment()));
        sub_list2.add(new SideVO("수강신청", new RegistListFragment()));
        main_list.add(new SideVO("강의 관리","" , "#1597BB"  , sub_list2 ));
        main_list.get(1).setImageId(R.drawable.menuimg2);     // 내 정보

        main_list.get(2).setImageId(R.drawable.menuimg3); // 성적관리


        ArrayList<SideVO> sub_list4 = new ArrayList<>();
        sub_list4.add(new SideVO("공지사항", new NoticeFragment()));
        sub_list4.add(new SideVO("자유게시판", new BoardFragment()));
        main_list.add(new SideVO("게시판","" , "#4E9F3D"  , sub_list4 ));
        main_list.get(3).setImageId(R.drawable.menuimg4);     // 강의 관리
        main_list.get(4).setImageId(R.drawable.menuimg5);   // 게시판
        return main_list;
    }

    public ArrayList<SideVO>  getAdminList(){

        ArrayList<SideVO> main_list = new ArrayList<>();

        ArrayList<SideVO> sub_list1 = new ArrayList<>();
        main_list.add(new SideVO("내 정보","" , "#123456"  , sub_list1 , new MyinfoFragment()));
        main_list.add(new SideVO("비품관리","" , "#123456"  , sub_list1 , new EquipmentFragment()));
        main_list.add(new SideVO("학생 성적 확인", "" , "#661234", sub_list1, new ScoreTeacherFragment() ));
        main_list.get(0).setImageId(R.drawable.menuimg1);
        main_list.get(1).setImageId(R.drawable.menuimg2);


        ArrayList<SideVO> sub_list2 = new ArrayList<>();
        sub_list2.add(new SideVO("전체 강의목록", new LectureFragment()));
        sub_list2.add(new SideVO("내 강의목록", new Lecture_StuFragment()));
        sub_list2.add(new SideVO("내 시간표", new TimeTableFragment()));
        sub_list2.add(new SideVO("수강신청", new RegistListFragment()));
        main_list.add(new SideVO("강의 관리","" , "#654321"  , sub_list2 ));
        main_list.get(2).setImageId(R.drawable.menuimg3);


        main_list.get(3).setImageId(R.drawable.menuimage3);


        ArrayList<SideVO> sub_list4 = new ArrayList<>();
        sub_list4.add(new SideVO("공지사항", new NoticeFragment()));
        sub_list4.add(new SideVO("자유게시판", new BoardFragment()));
        main_list.add(new SideVO("게시판","" , "#661234"  , sub_list4 ));
        main_list.get(4).setImageId(R.drawable.menuimg4);

        return main_list;
    }
}