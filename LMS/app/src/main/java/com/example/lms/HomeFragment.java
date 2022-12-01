package com.example.lms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;


public class HomeFragment extends Fragment implements OnMapReadyCallback {
CardView eqlist;
    NaverMap naverMap;
    MapView map_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        eqlist = v.findViewById(R.id.eqlist);
        eqlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        map_view = v.findViewById(R.id.map_view);
        NaverMapSdk.getInstance(getContext()).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("mohdfmnbxf")
        );
        map_view.getMapAsync(this);


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
}