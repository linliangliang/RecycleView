package com.zhengyuan.recyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zhengyuan.recyclerview.entity.WaterFallItemEntity;
import com.zhengyuan.recyclerview.loadNetImageActivityAdapter.WaterFallAdapter;
import com.zhengyuan.recyclerview.util.ImageUtil;
import com.zhengyuan.recyclerview.util.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.zhengyuan.recyclerview.util.ImageUtil.imageUrls;

/**
 * Created by 林亮 on 2019/1/7
 */

public class LoadNetworkIamgeActivity extends Activity {

    private RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<WaterFallItemEntity> waterFallItemEntities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();//假装从服务器获取数据，这里的图片网址写死

        StaggeredGridLayout();//使用瀑布流布局
    }

    private void StaggeredGridLayout() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new WaterFallAdapter(this, waterFallItemEntities));
        //mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, 2));//添加分割线
    }


    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);//上下拉刷新加载列表
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

    }

    private void initData() {
        waterFallItemEntities = new ArrayList<WaterFallItemEntity>();
        for (int i = 0; i < ImageUtil.imageUrls.length; i++) {
            waterFallItemEntities.add(new WaterFallItemEntity("也许你现在仍然是一个人下班，一个人乘地铁，一个人上楼，一个人吃饭，一个人睡觉，一个人发呆。然而你却能一个人下班，一个人乘地铁，一个人上楼，一个人吃饭，一个人睡觉，一个人发呆。很多人离开另外一个人，就没有自己。而你却一个人，度过了所有。你的孤独，虽败犹荣。"
                    , ImageUtil.imageUrls[i], "2019-1-" + (int) (Math.random() * 10)));
        }
    }
}
