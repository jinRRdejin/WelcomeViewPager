package com.zhangxt4.welcomeviewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 实现A页角度0~-20的旋转动画
 * B页角度20~0的旋转动画
 * Created by zhangxt4 on 2015/12/30.
 */
public class RotatePageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_ROTATE = 20f;
    private float mRot;
    private static final String TAG = "RotatePageTransformer";


    @Override
    public void transformPage(View view, float position) {
        Log.d(TAG, "view = " + view + ", position = " + position);
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left. 不旋转
            ViewHelper.setRotation(view, 0);

        } else if (position <= 0) { // [-1,0], 第一页position: 0~-1
            // Use the default slide transition when moving to the left page
            mRot = position * MAX_ROTATE; //0~-20度
            ViewHelper.setPivotX(view, pageWidth/2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());
            ViewHelper.setRotation(view, mRot);

        } else if (position <= 1) { // (0,1], 第二页position: 1~0
            // Fade the page out.
            mRot = position * MAX_ROTATE; //20~0度
            ViewHelper.setPivotX(view, pageWidth/2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());
            ViewHelper.setRotation(view, mRot);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setRotation(view, 0);
        }
    }
}
