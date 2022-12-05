package com.example.lms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;


import com.example.lms.board.BoardFragment;
import com.example.lms.myinfo.MyinfoFragment;
import com.example.lms.notice.NoticeFragment;
import com.example.lms.timetable.TimeTableFragment;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;


public class HomeFragment extends Fragment implements OnMapReadyCallback {
CardView my_info, time_table, board, notice;
    ViewFlipper flipper;
    NaverMap naverMap;
    MapView map_view;
    ViewFlipper viewflipper;
    ImageView qnet, hrdnet, goyong, worknet, kgoyong, worldjob, hrdk;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        time_table = v.findViewById(R.id.time_table);
        board = v.findViewById(R.id.board);
        notice = v.findViewById(R.id.notice);
        my_info = v.findViewById(R.id.my_info);
        flipper = v.findViewById(R.id.flipper);
        // -------------------------- 찾기 ------------------------------//

        int benner_img[] = {
                R.drawable.cimg1,
                R.drawable.cimg2,
                R.drawable.cimg3,
        };      //배너 플리퍼 리스트
        for(int image : benner_img) {
            flipperImages(image);
        }   //리스트를 플리퍼 실행 메소드에 반복



        // -------------------------- 버튼 -----------------------------//
        my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyinfoFragment()).commit();
            }
        });
        time_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeTableFragment()).commit();
            }
        });
        board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BoardFragment()).commit();
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new NoticeFragment()).commit();
            }
        });


        map_view = v.findViewById(R.id.map_view);
        NaverMapSdk.getInstance(getContext()).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("mohdfmnbxf")
        );
        map_view.getMapAsync(this);


        viewflipper = v.findViewById(R.id.viewflipper);
        hrdnet = v.findViewById(R.id.hrdnet);
        qnet = v.findViewById(R.id.qnet);
        goyong = v.findViewById(R.id.goyong);
        worknet = v.findViewById(R.id.worknet);
        kgoyong = v.findViewById(R.id.kgoyong);
        worldjob = v.findViewById(R.id.worldjob);
        hrdk = v.findViewById(R.id.hrdk);
        qnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.q-net.or.kr/man001.do?imYn=Y&gSite=Q"));
                startActivity(intent);
            }
        });
        hrdnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hrd.go.kr/hrdp/ma/pmmao/newIndexRenewal.do"));
                startActivity(intent);
            }
        });
        goyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moel.go.kr/index.do"));
                startActivity(intent);
            }
        });
        worknet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.work.go.kr/seekWantedMain.do"));
                startActivity(intent);
            }
        });
        kgoyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.keis.or.kr/main/index.do"));
                startActivity(intent);
            }
        });
        worldjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worldjob.or.kr/new_index.do"));
                startActivity(intent);
            }
        });
        hrdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hrdkorea.or.kr/"));
                startActivity(intent);
            }
        });

        return v;
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap = naverMap;
        naverMap.setMapType(NaverMap.MapType.Basic);
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.1535583, 126.8879957))
                .animate(CameraAnimation.Easing);
        naverMap.moveCamera(cameraUpdate);
        Marker marker = new Marker();
        marker.setPosition(new LatLng(35.1535583, 126.8879957));
        marker.setMap(naverMap);
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);
        flipper.addView(imageView);
        flipper.setFlipInterval(3000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        flipper.setAutoStart(true);          // 자동 스타트




    }
}