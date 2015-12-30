package com.zhangxt4.welcomeviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private ViewPagerCompat mViewPager;
    private MyViewPager mViewPager;
    private int[] mImgIds = new int[]{R.mipmap.guide_image1, R.mipmap.guide_image2, R.mipmap.guide_image3};
    private List<ImageView> mImgViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
//        mViewPager = (ViewPagerCompat) findViewById(R.id.id_viewpager);
        mViewPager = (MyViewPager) findViewById(R.id.id_viewpager);
        //为ViewPager添加动画效果(3.0以上版本)
//        mViewPager.setPageTransformer(true, new RotatePageTransformer());
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(new MyPagerAdapter(mViewPager, mImgViews, mImgIds, MainActivity.this));
    }
}
