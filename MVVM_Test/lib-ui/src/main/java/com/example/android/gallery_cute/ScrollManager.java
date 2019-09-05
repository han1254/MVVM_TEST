package com.example.android.gallery_cute;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.example.android.utils.AppRuntime;
import com.example.android.utils.DLog;
import com.example.android.utils.OsUtil;


/**
 * @author RyanLee
 * @date 2017/12/8
 */

public class ScrollManager {
    private static final String TAG = "MainActivity_TAG";

    private GalleryRecyclerView mGalleryRecyclerView;

    private int mPosition = 0;

    private int time;

    /**
     * x方向消耗距离，使偏移量为左边距 + 左边Item的可视部分宽度
     */
    private int mConsumeX = 0;
    private int mConsumeY = 0;

    ScrollManager(GalleryRecyclerView mGalleryRecyclerView) {
        this.mGalleryRecyclerView = mGalleryRecyclerView;
    }

    /**
     * 初始化SnapHelper
     *
     * @param helper int
     */
    public void initSnapHelper(int helper) {
        switch (helper) {
            case GalleryRecyclerView.LINEAR_SNAP_HELPER:
                LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
                mLinearSnapHelper.attachToRecyclerView(mGalleryRecyclerView);
                break;
            case GalleryRecyclerView.PAGER_SNAP_HELPER:
                PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
                mPagerSnapHelper.attachToRecyclerView(mGalleryRecyclerView);
                break;
            default:
                break;
        }
    }

    /**
     * 监听RecyclerView的滑动
     */
    public void initScrollListener() {
        GalleryScrollerListener mScrollerListener = new GalleryScrollerListener();
        mGalleryRecyclerView.addOnScrollListener(mScrollerListener);
    }

    public void updateConsume() {
        mConsumeX += OsUtil.dpToPx(mGalleryRecyclerView.getDecoration().mLeftPageVisibleWidth + mGalleryRecyclerView.getDecoration().mPageMargin * 2);
        mConsumeY += OsUtil.dpToPx(mGalleryRecyclerView.getDecoration().mLeftPageVisibleWidth + mGalleryRecyclerView.getDecoration().mPageMargin * 2);
        DLog.d(TAG, "ScrollManager updateConsume mConsumeX=" + mConsumeX);
    }

    class GalleryScrollerListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            DLog.d(TAG, "ScrollManager newState=" + newState);
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (mGalleryRecyclerView.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                SharedPreferences sp = AppRuntime.getAppContext().getSharedPreferences("time",Context.MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst",true);
                if (isFirst){
                    mConsumeX = 0;
                    mConsumeY = 0;
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isFirst",false);
                    editor.apply();
                }
                onHorizontalScroll(recyclerView, dx);
            } else {
                onVerticalScroll(recyclerView, dy);
            }
        }
    }

    /**
     * 垂直滑动
     *
     * @param recyclerView RecyclerView
     * @param dy           int
     */
    private void onVerticalScroll(final RecyclerView recyclerView, int dy) {

        mConsumeY += dy;

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemHeight的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeY = mGalleryRecyclerView.getDecoration().mItemConsumeY;

                // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                float offset = (float) mConsumeY / (float) shouldConsumeY;
                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);

                mPosition = (int) offset;

                DLog.i(TAG, "ScrollManager offset=" + offset + "; mConsumeY=" + mConsumeY + "; shouldConsumeY=" + mPosition);

                mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);

//               if(time == 1){
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }else if(time == 2){
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }else{
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }
            }
        });
    }

    /**
     * 水平滑动
     *
     * @param recyclerView RecyclerView
     * @param dx           int
     */
    private void onHorizontalScroll(final RecyclerView recyclerView, final int dx) {


        SharedPreferences sp = AppRuntime.getAppContext().getSharedPreferences("time", Context.MODE_PRIVATE);

        time = sp.getInt("time",1);

        mConsumeX += dx;

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemWidth的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeX = mGalleryRecyclerView.getDecoration().mItemConsumeX;

                // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                float offset = (float) Math.abs(mConsumeX) / (float)shouldConsumeX;


                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);

                mPosition = (int) offset;

//                int realposition = mConsumeX/mGalleryRecyclerView.getDecoration().getItemWidth();
//
//                int realOffset = mConsumeX - (mConsumeX/mGalleryRecyclerView.getDecoration().getItemWidth()) * realposition;
//
//                float realPercent = (float)Math.max((Math.abs(realOffset)* 0.1/shouldConsumeX),0.00001);

//                mPosition = realposition;

                DLog.i(TAG, "ScrollManager offset=" + offset + "; percent=" + percent + "; mConsumeX=" + mConsumeX + "; shouldConsumeX=" + shouldConsumeX + "; position=" + mPosition);


//                if(time == 1 ){
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }else if(time == 2){
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }else{
//                   // 设置动画变化
//                   mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
//               }

//                // 设置动画变化
//                mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
                if(mPosition == 3){
                    mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
                }else {
                    mGalleryRecyclerView.getAnimManager().setAnimation(recyclerView, mPosition, percent);
                }

            }
        });

    }

    public int getPosition() {
        return mPosition;
    }
}