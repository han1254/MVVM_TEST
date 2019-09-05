package com.example.android.ui.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author YangZhaoxin.
 * @since 2019/8/29 18:35.
 * email yangzhaoxin@hrsoft.net.
 */

public class GalleryRecyclerView extends RecyclerView {

    private static final float FLING_SCALE_DOWN_FACTOR = 0.5f;  // 减速因子
    private static final int FLING_MAX_VELOCITY = 8000;    // 最大顺时滑动速度

    private GalleryConfig mConfig = new GalleryConfig.Builder().build();

    public GalleryRecyclerView(@NonNull Context context) {
        super(context);
    }

    public GalleryRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = getVelocity(velocityX);
        velocityY = getVelocity(velocityY);
        return super.fling(velocityX, velocityY);
    }

    private int getVelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, FLING_MAX_VELOCITY);
        } else {
            return Math.max(velocity, -FLING_MAX_VELOCITY);
        }
    }

    public GalleryConfig getConfig() {
        return mConfig;
    }

    public void setConfig(GalleryConfig config) {
        mConfig = config;
    }
}
