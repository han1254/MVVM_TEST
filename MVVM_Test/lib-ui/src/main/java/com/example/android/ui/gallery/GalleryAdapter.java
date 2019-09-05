package com.example.android.ui.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.utils.ScreenUtil;

import java.util.List;

/**
 * @author YangZhaoxin.
 * @since 2019/8/30 9:14.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class GalleryAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<T> mDatas;

    private int mPagePadding;
    private int mShowLeftCardWidth;

    public GalleryAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    public void setConfig(GalleryConfig config) {
        mPagePadding = config.pagePadding;
        mShowLeftCardWidth = config.showLeftCardWidth;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), viewGroup, false);

        // 设置 adapter 宽度
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        params.width = viewGroup.getWidth() - ScreenUtil.dip2px(view.getContext(), 2 * (mPagePadding + mShowLeftCardWidth));
        view.setLayoutParams(params);

        return onCreateGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        // 计算 adapter 间距
        View itemView = vh.itemView;
        int padding = ScreenUtil.dip2px(itemView.getContext(), mPagePadding);
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = i == 0 ? padding + ScreenUtil.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
        int rightMarin = i == getItemCount() - 1 ? padding + ScreenUtil.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);

        onBindGalleyViewHolder(vh, i);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (params.leftMargin != left || params.topMargin != top || params.rightMargin != right || params.bottomMargin != bottom) {
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);
        }
    }

    protected abstract int getLayoutId();

    protected abstract VH onCreateGalleryViewHolder(View itemView);

    protected abstract void onBindGalleyViewHolder(VH vh, int i);

}
