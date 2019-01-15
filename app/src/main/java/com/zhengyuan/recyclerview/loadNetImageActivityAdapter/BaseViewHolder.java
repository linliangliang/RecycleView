package com.zhengyuan.recyclerview.loadNetImageActivityAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhengyuan.recyclerview.entity.IData;

/**
 * Created by 林亮 on 2019/1/15
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    //数据类型接口IData.java,传递给adapter的数据要实现该接口
    public void FillView(IData data) {
    }
}
