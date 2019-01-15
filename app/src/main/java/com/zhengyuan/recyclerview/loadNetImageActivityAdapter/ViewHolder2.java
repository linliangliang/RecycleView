package com.zhengyuan.recyclerview.loadNetImageActivityAdapter;

import android.view.View;
import android.widget.TextView;

import com.zhengyuan.recyclerview.R;
import com.zhengyuan.recyclerview.entity.IData;
import com.zhengyuan.recyclerview.entity.Info2;

/**
 * Created by 林亮 on 2019/1/15
 */

public class ViewHolder2 extends BaseViewHolder {
    private TextView title;

    public ViewHolder2(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.text1_info2);

    }

    @Override
    public void FillView(IData data) {
        super.FillView(data);
        title.setText(((Info2) data).getTitle());
    }
}
