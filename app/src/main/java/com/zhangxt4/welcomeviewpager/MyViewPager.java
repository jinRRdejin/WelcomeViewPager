package com.zhangxt4.welcomeviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxt4 on 2015/12/30.
 */
public class MyViewPager extends ViewPager {
    private View mLeftView;
    private View mRightView;
    //两个动画效果
    private float mTrans; //移动
    private float mScale; //缩放
    //梯度值
    private static final float MIN_SCALE = 0.6f; //缩放梯度值
    //保存当前左、右两个view，和position对应，它会在MyPagerAdapter中初始化
    private Map<Integer, View> mChildren = new HashMap<>();
    private static final String TAG = "MyViewPager";

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setViewForPosition(View view, int position){
        mChildren.put(position, view);
    }
    public void removeViewFromPosition(Integer position){
        mChildren.remove(position);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        /* 页面0->1: position = 0; offset = 0~1; offsetPixels = 0~屏幕宽度
         * 页面1->0: position = 0; offset = 1~0; offsetPixels = 屏幕宽度~0
         * offsetPixels随着offset变化（像素值）,offsetPixels = offset*屏幕宽度
         * 右边的view位置就是position+1
         */
        Log.d(TAG, "position = " + position + ", offset = " + offset + ", offsetPixels = " + offsetPixels);
        mLeftView = mChildren.get(position);
        mRightView = mChildren.get(position + 1); //右面的view位置就是position+1

        if (mRightView != null){
            //右边view: mScale = 0.6~1
            //mTrans = -屏幕宽度~0,因为刚开始rightview应该是往左偏了一个屏幕宽度
            mScale = MIN_SCALE + (1-MIN_SCALE) * offset;
            mTrans = -getWidth() - getPageMargin() + offsetPixels;
            ViewHelper.setScaleX(mRightView, mScale);
            ViewHelper.setScaleY(mRightView, mScale);
            ViewHelper.setTranslationX(mRightView, mTrans);
        }
        if (mLeftView != null){
            mLeftView.bringToFront();
        }
        super.onPageScrolled(position, offset, offsetPixels);
    }
}
