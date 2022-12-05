package com.example.lms.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.lms.R;

public class HomeViewActivity extends AppCompatActivity {
    public static final int NUM_PAGES =3;
    private ViewPager2 pager;
    private FragmentStateAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        pager = findViewById(R.id.carouselView);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        pager.setAdapter(pagerAdapter);
    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter{
        public ScreenSlidePagerAdapter(FragmentActivity fa){
            super (fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {//포지션마다 있을 fragment설정
           if(position==0) return new page1Fragment();
            else if(position==1) return new page2Fragment();
            else return new page3Fragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;  //페이지 수 지정.
        }
    }
}