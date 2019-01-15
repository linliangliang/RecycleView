package com.zhengyuan.recyclerview.entity;

import com.zhengyuan.recyclerview.constant.RecycleItemTypeConstant;

/**
 * Created by 林亮 on 2019/1/15
 */
public class Info2 implements IData {
    @Override
    public int typ() {
        return RecycleItemTypeConstant.RECYCLEVIEW_ITEM_TYPE_2;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
