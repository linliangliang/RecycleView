package com.zhengyuan.recyclerview.entity;

import com.zhengyuan.recyclerview.constant.RecycleItemTypeConstant;

/**
 * Created by 林亮 on 2019/1/15
 */

public class Info1 implements IData {
    @Override
    public int typ() {
        return RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_1;
    }

    private int left_icon_id;
    private String mContent;
    private String mImagedesc;
    private String mTime;

    public int getLeft_icon_id() {
        return left_icon_id;
    }

    public void setLeft_icon_id(int left_icon_id) {
        this.left_icon_id = left_icon_id;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmImagedesc() {
        return mImagedesc;
    }

    public void setmImagedesc(String mImagedesc) {
        this.mImagedesc = mImagedesc;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
