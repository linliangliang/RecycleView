package com.zhengyuan.recyclerview.loadNetImageActivityAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhengyuan.recyclerview.R;
import com.zhengyuan.recyclerview.entity.IData;
import com.zhengyuan.recyclerview.entity.Info1;

/**
 * Created by 林亮 on 2019/1/15
 */

public class ViewHolder1 extends BaseViewHolder {

    private ImageView leftIcon;
    private TextView mContent;
    private TextView mImagedesc;
    private TextView mTime;

    public ViewHolder1(View itemView) {
        super(itemView);
        leftIcon = itemView.findViewById(R.id.image_info1);
        mContent = itemView.findViewById(R.id.tv_content_info1);
        mImagedesc = itemView.findViewById(R.id.tv_imagedesc_info1);
        mTime = itemView.findViewById(R.id.time_info1);
    }

    @Override
    public void FillView(IData data) {
        super.FillView(data);
        if (data instanceof Info1) {
            leftIcon.setImageResource(((Info1) data).getLeft_icon_id());
            mContent.setText(((Info1) data).getmContent());
            mImagedesc.setText(((Info1) data).getmImagedesc());
            mTime.setText(((Info1) data).getmTime());
        }
    }
}