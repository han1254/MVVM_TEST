package com.example.android.ui.gallery;

/**
 * @author YangZhaoxin.
 * @since 2019/8/30 8:09.
 * email yangzhaoxin@hrsoft.net.
 */

public class GalleryConfig {

    public float scale;    // 两边视图 scale
    public int pagePadding;  // 卡片的 padding，卡片间的距离等于2倍的 pagePadding
    public int showLeftCardWidth;    // 左边卡片显示大小

    private GalleryConfig(float scale, int pagePadding, int showLeftCardWidth) {
        this.scale = scale;
        this.pagePadding = pagePadding;
        this.showLeftCardWidth = showLeftCardWidth;
    }

    public static final class Builder {
        private float mScale = 0.8f;
        private int mPagePadding = 15;
        private int showLeftCardWidth = 60;

        public Builder setScale(float scale) {
            mScale = scale;
            return this;
        }

        public Builder setPagePadding(int pagePadding) {
            mPagePadding = pagePadding;
            return this;
        }

        public Builder setShowLeftCardWidth(int showLeftCardWidth) {
            this.showLeftCardWidth = showLeftCardWidth;
            return this;
        }

        public GalleryConfig build() {
            return new GalleryConfig(mScale, mPagePadding, showLeftCardWidth);
        }
    }
}