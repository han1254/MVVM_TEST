package com.example.android.ui.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.utils.ScreenUtil;

/**
 * @author YangZhaoxin.
 * @since 2019/8/29 19:52.
 * email yangzhaoxin@hrsoft.net.
 */

public class GalleryController {
    private GalleryRecyclerView mRecyclerView;
    private Context mContext;

    private float mScale;    // 两边视图 scale
    private int mPagePadding;  // 卡片的 padding，卡片间的距离等于2倍的 pagePadding
    private int mShowLeftCardWidth;    // 左边卡片显示大小

    private int mCardWidth;     // 卡片宽度
    private int mOnePageWidth;  // 滑动一页的距离
    private int mGalleryWidth;

    private int mCurrentItemPos;
    private int mCurrentItemOffset;

    private CardLinearSnapHelper mLinearSnapHelper = new CardLinearSnapHelper();
    private GalleryConfig mConfig;

    public void attachToRecyclerView(GalleryRecyclerView recyclerView) {

        if (recyclerView.getOnFlingListener() != null){
            recyclerView.setOnFlingListener(null);
        }

        initConfig(recyclerView.getConfig());
        this.mRecyclerView = recyclerView;

        mContext = mRecyclerView.getContext();
        // 传递参数
        setTransConfig();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {   // 滚动停止时，如果属于第一个和最后一个，将其设置为可以不居中
                    mLinearSnapHelper.mNoNeedToScroll = mCurrentItemOffset == 0 ||
                            mCurrentItemOffset == getDestItemOffset(mRecyclerView.getAdapter().getItemCount() -1);
                } else {
                    mLinearSnapHelper.mNoNeedToScroll = false;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dx > 0 表示右滑，dx < 0 表示左滑
                if (dx != 0) {
                    mCurrentItemOffset += dx;
                    computeCurrentItemPos();
                    onScrolledViewChanged();
                }
            }
        });

        initWidth();
        // 使RecyclerView滑动视图后让当前item停留在当前页的正中间

        mRecyclerView.setOnFlingListener(null);
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initConfig(GalleryConfig config) {
        mConfig = config;
        mScale = mConfig.scale;
        mPagePadding = mConfig.pagePadding;
        mShowLeftCardWidth = mConfig.showLeftCardWidth;
    }

    private int getDestItemOffset(int destPos) {
        return mOnePageWidth * destPos;
    }

    /**
     * 计算当前currentItemPos
     */
    private void computeCurrentItemPos() {
        if (mOnePageWidth <= 0) return;
        boolean pageChanged = false;
        // 滑动超过一页说明已翻页
        if (Math.abs(mCurrentItemOffset - mCurrentItemPos * mOnePageWidth) >= mOnePageWidth) {
            pageChanged = true;
        }
        if (pageChanged) {
            mCurrentItemPos = mCurrentItemOffset / mOnePageWidth;
        }
    }

    /**
     * RecyclerView 位移事件监听，view大小随事件变化
     */
    private void onScrolledViewChanged() {
        int offset = mCurrentItemOffset - mCurrentItemPos * mOnePageWidth;
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.00001);

        View leftView = null;
        View currentView = null;
        View rightView = null;
        if (mCurrentItemPos > 0) {
            leftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos);
        if (mCurrentItemPos < mRecyclerView.getAdapter().getItemCount() - 1) {
            rightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 1);
        }

        if (leftView != null) {
            // y = (1 - mScale)x + mScale
            leftView.setScaleY((1 - mScale) * percent + mScale);
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setScaleY((mScale - 1) * percent + 1);
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.setScaleY((1 - mScale) * percent + mScale);
        }
    }

    /**
     * 初始化卡片宽度
     */
    private void initWidth() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mGalleryWidth = mRecyclerView.getWidth();
                mCardWidth = mGalleryWidth - ScreenUtil.dip2px(mContext, 2 * (mPagePadding + mShowLeftCardWidth));
                mOnePageWidth = mCardWidth;
                mRecyclerView.smoothScrollToPosition(mCurrentItemPos);
                onScrolledViewChanged();
            }
        });
    }

    public int getCurrentItemPos() {
        return mCurrentItemPos;
    }

    public void setCurrentItemPos(int currentItemPos) {
        mCurrentItemPos = currentItemPos;
    }

    private void setTransConfig() {
        ((GalleryAdapter) mRecyclerView.getAdapter()).setConfig(mConfig);
    }

}
