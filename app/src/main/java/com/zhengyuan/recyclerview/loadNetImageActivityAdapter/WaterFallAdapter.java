package com.zhengyuan.recyclerview.loadNetImageActivityAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhengyuan.recyclerview.MainActivity;
import com.zhengyuan.recyclerview.R;
import com.zhengyuan.recyclerview.entity.WaterFallItemEntity;
import com.zhengyuan.recyclerview.util.ImageUtil;

import java.util.List;

/**
 * Created by 林亮 on 2019/1/14
 */
//瀑布流适配器
public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    private Context mContext;
    private List<WaterFallItemEntity> mItemEntitys;

    public WaterFallAdapter(Context content, List<WaterFallItemEntity> itemEntitys) {
        this.mContext = content;
        this.mItemEntitys = itemEntitys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取item_layout的布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_water_fall, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitle.setText(mItemEntitys.get(position).getmTitle());
        //用glide加载网络图片 并放入imageview中
        Glide.with(mContext).load(mItemEntitys.get(position).getmImageUri()).into(holder.mImageview);
        holder.mTime.setText(mItemEntitys.get(position).getmTime());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "个卡片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置图片的点击事件
    private void setItemListener(final ViewHolder holder, final int position, final String url) {
        //如果holder为空 return；
        if (holder == null) {
            return;
        }
        //每个图片的点击事件
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示大图功能在说
                Toast.makeText(mContext, ">>>", Toast.LENGTH_SHORT).show();
                /*int[] location = new int[2];
                holder.mImageview.getLocationOnScreen(location);
                Intent it = new Intent(mContext, ImageActivity.class);
                String optionName = "transition_image";
                //获取图片的宽高
                int width = holder.imageview.getWidth();
                int height = holder.imageview.getHeight();
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, holder.imageview, optionName);
                //利用Bundle蒋点击的图片的网址传递过去
                Bundle bundle = compat.toBundle();
                if (bundle != null) {
                    //bundle.putString(ImageActivity.IMAGE_TAG, url);
                }
                it.putExtras(bundle);
                ActivityCompat.startActivity(mContext, it, bundle);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemEntitys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTitle;
        ImageView mImageview;
        TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mCardView = (CardView) view.findViewById(R.id.cv_item);
            mTitle = (TextView) view.findViewById(R.id.title);
            mImageview = (ImageView) view.findViewById(R.id.image);
            mTime = (TextView) view.findViewById(R.id.time);
        }
    }


    public void AddFooterItem(List<WaterFallItemEntity> footer) {
        mItemEntitys.addAll(footer);
        notifyDataSetChanged();
    }
}
