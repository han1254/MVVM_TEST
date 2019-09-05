package com.example.mvvm_test.bussiness.test.mvvm.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.gallery_cute.AnimManager;
import com.example.android.ui.gallery.GalleryAdapter;
import com.example.android.ui.gallery.GalleryController;
import com.example.android.ui.gallery.GalleryRecyclerView;
import com.example.mvvm_test.R;
import com.example.mvvm_test.bussiness.test.mvvm.model.PriceModel;
import com.example.mvvm_test.bussiness.test.mvvm.view.PriceChartIVewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager;

public class PriceTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_CHART = 0;
    public static final int TYPE_LIST_HEAD = 1;
    public static final int TYPE_LIST = 2;
    public static final int TYPE_ALL_HEAD = 3;
    public static final int TYPE_ALL = 4;
    public static final int TYPE_GALLERY = 5;
    public static final int TYPE_GALLERY_MANAGER = 6;
    private int dp8;
    private int dp03;


    private List<PriceModel> priceModels = new ArrayList<>();
    private Context context;


    public PriceTestAdapter(Context context, List<PriceModel> priceModels) {
        this.priceModels = priceModels;
        this.context = context;
        dp8 = Dp2Px(context, 8);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view;

        switch (i) {
            case TYPE_CHART:
                view = inflater.inflate(R.layout.item_price_uni_chartveiw, viewGroup, false);
//                return new CharViewHodlder(view);

//                return new PriceChartIVewHolder(view);

            case TYPE_LIST_HEAD:
                view = inflater.inflate(R.layout.item_price_uni_list_head, viewGroup, false);
                return new ListHeadHolder(view);
            case TYPE_LIST:
                view = inflater.inflate(R.layout.item_price_uni_list, viewGroup, false);
                return new ListHodlder(view);
            case TYPE_ALL_HEAD:
                view = inflater.inflate(R.layout.item_price_uni_all_head, viewGroup, false);
                return new AllHeadHolder(view);
            case TYPE_GALLERY:
                view = inflater.inflate(R.layout.item_price_uni_gallery, viewGroup, false);
                return new GalleryViewHolder(view);
            case TYPE_GALLERY_MANAGER:
                view = inflater.inflate(R.layout.price_uni_cute_gallery, viewGroup, false);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//                int width = context.getResources().getDisplayMetrics().widthPixels;
//                params.width = width/3;
//                view.setLayoutParams(params);
                return new CuteGalleryViewHolder(view);
            default:
                view = inflater.inflate(R.layout.item_price_uni_all, viewGroup, false);
                return new AllHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof PriceChartIVewHolder) {
            ((PriceChartIVewHolder) holder).bind(null, i, context);
        } else if (holder instanceof GalleryViewHolder) {
            toBindGalleryViewHolder(((GalleryViewHolder) holder).mGalleryRecycler);
        } else if (holder instanceof GalleryManagerViewHolder) {
            toBindGalleryMangaerViewHolder(((GalleryManagerViewHolder) holder).mGalleryManagerRecycler);
        }else if(holder instanceof CuteGalleryViewHolder){
            toBindCuteGalleryViewHolder(((CuteGalleryViewHolder) holder).mRvList);
        }
    }


    private void toBindCuteGalleryViewHolder(com.example.android.gallery_cute.GalleryRecyclerView mRvList) {


        SharedPreferences sp = context.getSharedPreferences("time",Context.MODE_PRIVATE);
        //用于测试
        int time = sp.getInt("time",0);
        SharedPreferences.Editor editor = sp.edit();

        //存储是否是刚滑动到该部分，如果是，则在ScrollManager中将之前累加的x与y的偏移量清空
        editor.putBoolean("isFirst",true);

        //用于测试
        editor.putInt("time",++time);
        editor.apply();
        ;

        List<Integer> mDatas = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            mDatas.add(R.drawable.ic_test);
        }
        CuteGalleryAdapter adapter = new CuteGalleryAdapter(context,mDatas);
        mRvList.setAdapter(adapter);
        mRvList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));


        mRvList // 设置滑动速度（像素/s）
                .initFlingSpeed(9000)
                // 设置页边距和左右图片的可见宽度，单位dp
                .initPageParams(0, 40)
                // 设置切换动画的参数因子
                .setAnimFactor(0.1f)
                // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
                // 设置自动播放
                .autoPlay(false)
                // 设置自动播放间隔时间 ms
                .intervalTime(2000)
                // 设置初始化的位置
                .initPosition(mDatas.size()/2)
                // 在设置完成之后，必须调用setUp()方法
                .setUp();

//      if(sp.getInt("time",1) == 1){
//          mRvList // 设置滑动速度（像素/s）
//                  .initFlingSpeed(9000)
//                  // 设置页边距和左右图片的可见宽度，单位dp
//                  .initPageParams(0, 40)
//                  // 设置切换动画的参数因子
//                  .setAnimFactor(0.1f)
//                  // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
//                  .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
//                  // 设置点击事件
//                  // 设置自动播放
//                  .autoPlay(false)
//                  // 设置自动播放间隔时间 ms
//                  .intervalTime(2000)
//                  // 设置初始化的位置
//                  .initPosition(1)
//                  // 在设置完成之后，必须调用setUp()方法
//                  .setUp();
//
//      }else if(sp.getInt("time",1) == 2){
//          mRvList // 设置滑动速度（像素/s）
//                  .initFlingSpeed(9000)
//                  // 设置页边距和左右图片的可见宽度，单位dp
//                  .initPageParams(0, 40)
//                  // 设置切换动画的参数因子
//                  .setAnimFactor(0.1f)
//                  // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
//                  .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
//                  // 设置点击事件
//                  // 设置自动播放
//                  .autoPlay(false)
//                  // 设置自动播放间隔时间 ms
//                  .intervalTime(2000)
//                  // 设置初始化的位置
//                  .initPosition(1)
//                  // 在设置完成之后，必须调用setUp()方法
//                  .setUp();
//
//      }else if(sp.getInt("time",1) == 3){
//          mRvList // 设置滑动速度（像素/s）
//                  .initFlingSpeed(9000)
//                  // 设置页边距和左右图片的可见宽度，单位dp
//                  .initPageParams(0, 40)
//                  // 设置切换动画的参数因子
//                  .setAnimFactor(0.1f)
//                  // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
//                  .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
//                  // 设置点击事件
//                  // 设置自动播放
//                  .autoPlay(false)
//                  // 设置自动播放间隔时间 ms
//                  .intervalTime(2000)
//                  // 设置初始化的位置
//                  .initPosition(1)
//                  // 在设置完成之后，必须调用setUp()方法
//                  .setUp();
//
//      }else{
//          mRvList // 设置滑动速度（像素/s）
//                  .initFlingSpeed(9000)
//                  // 设置页边距和左右图片的可见宽度，单位dp
//                  .initPageParams(0, 40)
//                  // 设置切换动画的参数因子
//                  .setAnimFactor(0.1f)
//                  // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
//                  .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
//                  // 设置点击事件
//                  // 设置自动播放
//                  .autoPlay(false)
//                  // 设置自动播放间隔时间 ms
//                  .intervalTime(2000)
//                  // 设置初始化的位置
//                  .initPosition(1)
//                  // 在设置完成之后，必须调用setUp()方法
//                  .setUp();
//
//      }
//




    }



    private void toBindGalleryMangaerViewHolder(RecyclerView recyclerView) {
        List<Drawable> mDrawables = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mDrawables.add(context.getResources().getDrawable(R.mipmap.ic_launcher_round));
        }

        GalleryLayoutManager manager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        manager.attach(recyclerView);
        manager.setItemTransformer(new MyTransformer());
        GalleryManagerAdapter adapter = new GalleryManagerAdapter(mDrawables, context);
        recyclerView.setAdapter(adapter);

    }

    class GalleryManagerAdapter extends RecyclerView.Adapter<GalleryManagerAdapter.ManagerViewHolder> {

        private List<Drawable> list;
        private Context context;

        public GalleryManagerAdapter(List<Drawable> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public ManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.galleryitem, viewGroup, false);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//            int width = context.getResources().getDisplayMetrics().widthPixels;
//            params.width = width/3;
//            view.setLayoutParams(params);
            return new ManagerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ManagerViewHolder managerViewHolder, int i) {
            managerViewHolder.ivBig.setImageDrawable(list.get(i));
        }


        @Override
        public int getItemCount() {
            return list.size();
        }


        class ManagerViewHolder extends RecyclerView.ViewHolder {
            //            View view = R.layout.galleryitem;
            @BindView(R.id.iv_big)
            ImageView ivBig;

            public ManagerViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


    class MyTransformer implements GalleryLayoutManager.ItemTransformer {
        @Override
        public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
            //以圆心进行缩放
            item.setPivotX(item.getWidth() / 2.0f);
            item.setPivotY(item.getHeight() / 2.0f);
            float scale = 1 - 0.3f * Math.abs(fraction);
            item.setScaleX(scale);
            item.setScaleY(scale);
        }
    }


    private void toBindGalleryViewHolder(GalleryRecyclerView galleryRecyclerView) {

        List<Drawable> mDrawables = new ArrayList<>();
        GalleryController controller = new GalleryController();
        for (int i = 0; i < 6; i++) {
            mDrawables.add(context.getResources().getDrawable(R.mipmap.ic_launcher_round));
        }

        MyAdapter adapter = new MyAdapter(context, mDrawables);
        galleryRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        galleryRecyclerView.setAdapter(adapter);

        controller.setCurrentItemPos(1);
        controller.attachToRecyclerView(galleryRecyclerView);

    }

    class MyAdapter extends GalleryAdapter<Drawable, MyAdapter.MyHolder> {

        public MyAdapter(Context context, List<Drawable> datas) {
            super(context, datas);
        }

        @Override
        protected int getLayoutId() {
            return 0;
        }

        @Override
        protected MyHolder onCreateGalleryViewHolder(View itemView) {
            return new MyHolder(itemView);
        }

        @Override
        protected void onBindGalleyViewHolder(MyHolder myHolder, int i) {
            Drawable drawable = mDatas.get(i);
            myHolder.mImageView.setImageDrawable(drawable);
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.iv_big);
            }
        }
    }


    @Override
    public int getItemCount() {
        int count = 2 + 3 + 1 + 12;
        return 20;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return TYPE_CHART;
            case 1:
                return TYPE_CHART;
            case 2:
                return TYPE_LIST_HEAD;
            case 3:
                return TYPE_LIST;
            case 4:
                return TYPE_LIST;
            case 5:
                return TYPE_LIST;
            case 6:
                return TYPE_ALL_HEAD;
            case 19:
                return TYPE_GALLERY_MANAGER;
//            case 20:
//                return TYPE_GALLERY_MANAGER;
            default:
                return TYPE_ALL;
        }

    }

    private boolean isCharViewExist() {
        return true;
    }

    private boolean isListExist() {
        return priceModels != null;

    }

    private boolean isAllExist() {

        return priceModels != null;

    }

    class CharViewHodlder extends RecyclerView.ViewHolder {

        private LinearLayout lineChart;

        public CharViewHodlder(@NonNull View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.price_linear_chart_content);
        }
    }

    class ListHeadHolder extends RecyclerView.ViewHolder {


        public ListHeadHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    class ListHodlder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_price_uni_tv_price)
        TextView mTvPrice;
        @BindView(R.id.item_price_uni_list_tv_range)
        TextView mRange;
        @BindView(R.id.item_price_uni_list_tv_speed)
        TextView mSpeed;
        @BindView(R.id.item_price_uni_list_selector_collect)
        ImageView mSelectorCollect;

        public ListHodlder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AllHeadHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_price_uni_all_head_relative_goto)
        RelativeLayout mRelativeGoto;

        public AllHeadHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AllHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_price_uni_all_img)
        ImageView mImg;
        @BindView(R.id.item_price_uni_all_tv_name)
        TextView mName;
        @BindView(R.id.item_price_uni_all_tv_price)
        TextView mPrice;
        @BindView(R.id.item_price_uni_all_tv_range)
        TextView mRange;
        @BindView(R.id.item_price_uni_all_tv_speed)
        TextView mSpeed;
        @BindView(R.id.item_price_uni_all_view)
        View mView;
        @BindView(R.id.clever_me)
        LinearLayout cleverMe;

        public AllHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

//        View view = R.layout.item_price_uni_gallery;

        @BindView(R.id.item_price_uni_gallery_recycler)
        GalleryRecyclerView mGalleryRecycler;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

    class GalleryManagerViewHolder extends RecyclerView.ViewHolder {
        //        View view = R.layout.item_price_uni_gallery_manager;
        @BindView(R.id.price_uni_gallery_manager_recycler)
        RecyclerView mGalleryManagerRecycler;

        public GalleryManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class CuteGalleryViewHolder extends RecyclerView.ViewHolder {

//        View v = R.layout.price_uni_cute_gallery;
        @BindView(R.id.cute_rv_list)
        com.example.android.gallery_cute.GalleryRecyclerView mRvList;

        public CuteGalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }


}
