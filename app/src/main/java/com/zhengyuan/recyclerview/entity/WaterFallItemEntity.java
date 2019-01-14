package com.zhengyuan.recyclerview.entity;

/**
 * Created by 林亮 on 2019/1/14
 */
//一个cardView 包含一个title,一个image,一个时间，一个内容（内容省略吧）和title一样
public class WaterFallItemEntity {

    private String mTitle;
    private String mImageUri;
    private String mTime;

    public WaterFallItemEntity(String title, String imageUri, String time) {
        mTitle = title;
        mImageUri = imageUri;
        mTime = time;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
