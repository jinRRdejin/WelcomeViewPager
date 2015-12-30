package com.zhangxt4.welcomeviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhangxt4 on 2015/12/29.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<ImageView> mImgViews;
    private int[] mImgIds;
    private Context mContext;

    public MyPagerAdapter(List<ImageView> imgViews, int[] imgIds, Context context) {
        this.mImgViews = imgViews;
        this.mImgIds = imgIds;
        this.mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mImgIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        mImgViews.add(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImgViews.get(position));
    }

    @Override
    public int getCount() {
        return mImgIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
