package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.lms.lecture.LectureFragment;
import com.example.lms.lms.CommonAskTask;
import com.example.lms.member.MemberVO;
import com.example.lms.notice.NoticeFragment;
import com.example.lms.sidemenu.SideAdapter;
import com.example.lms.sidemenu.SideVO;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

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
        }

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
        main_list.add(new SideVO("내 정보","(내정보 확인 , 수정 ... )" , "#123456"  , sub_list1 , new LectureFragment()));
        main_list.get(0).setImageId(R.drawable.menuimage1);


        ArrayList<SideVO> sub_list2 = new ArrayList<>();
        sub_list2.add(new SideVO("내 강의목록", new LectureFragment()));
        sub_list2.add(new SideVO("내 시간표", new LectureFragment()));
        main_list.add(new SideVO("강의 관리","(강의 목록 , 시간표 ... )" , "#654321"  , sub_list2 ));
        main_list.get(1).setImageId(R.drawable.menuimage2);

        ArrayList<SideVO> sub_list3 = new ArrayList<>();
        sub_list3.add(new SideVO("과제 등록", new LectureFragment()));
        sub_list3.add(new SideVO("시험문제 등록", new LectureFragment()));
        sub_list3.add(new SideVO("학생 성적 확인", new LectureFragment()));

        main_list.add(new SideVO("성적 관리","(과제 등록 , 학생 성적 확인... )" , "#661234"  , sub_list3 ));
        main_list.get(2).setImageId(R.drawable.menuimage3);


        ArrayList<SideVO> sub_list4 = new ArrayList<>();
        sub_list4.add(new SideVO("공지사항", new NoticeFragment()));
        sub_list4.add(new SideVO("학습자료", new LectureFragment()));
        sub_list4.add(new SideVO("수강후기", new LectureFragment()));
        main_list.add(new SideVO("게시판","(공지사항 , 학습 자료 게시판... )" , "#661234"  , sub_list4 ));
        main_list.get(3).setImageId(R.drawable.menuimage4);

        return main_list;
    }
    public ArrayList<SideVO>  getStudentList(){

        ArrayList<SideVO> main_list = new ArrayList<>();

        ArrayList<SideVO> sub_list1 = new ArrayList<>();
        main_list.add(new SideVO("내 정보","(내정보 확인 , 수정 ... )" , "#123456"  , sub_list1 , new LectureFragment()));
        main_list.get(0).setImageId(R.drawable.menuimage1);


        ArrayList<SideVO> sub_list2 = new ArrayList<>();
        sub_list2.add(new SideVO("내 강의목록", new LectureFragment()));
        sub_list2.add(new SideVO("내 시간표", new LectureFragment()));
        main_list.add(new SideVO("강의 관리","(강의 목록 , 시간표 ... )" , "#654321"  , sub_list2 ));
        main_list.get(1).setImageId(R.drawable.menuimage2);

        ArrayList<SideVO> sub_list3 = new ArrayList<>();
        sub_list3.add(new SideVO("과제 제출", new LectureFragment()));

        main_list.add(new SideVO("성적 관리","(과제 등록 , 학생 성적 확인... )" , "#661234"  , sub_list3 ));
        main_list.get(2).setImageId(R.drawable.menuimage3);


        ArrayList<SideVO> sub_list4 = new ArrayList<>();
        sub_list4.add(new SideVO("공지사항", new NoticeFragment()));
        sub_list4.add(new SideVO("학습자료", new LectureFragment()));
        sub_list4.add(new SideVO("수강후기", new LectureFragment()));

        main_list.add(new SideVO("게시판","(공지사항 , 학습 자료 게시판... )" , "#661234"  , sub_list4 ));
        main_list.get(3).setImageId(R.drawable.menuimage4);

        return main_list;
    }
}