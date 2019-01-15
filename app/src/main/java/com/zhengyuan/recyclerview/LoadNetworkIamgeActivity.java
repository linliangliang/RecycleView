package com.zhengyuan.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.zhengyuan.recyclerview.atapter.RecyclerViewAdapter;
import com.zhengyuan.recyclerview.entity.WaterFallItemEntity;
import com.zhengyuan.recyclerview.loadNetImageActivityAdapter.WaterFallAdapter;
import com.zhengyuan.recyclerview.util.ImageUtil;
import com.zhengyuan.recyclerview.util.SimpleDividerItemDecoration;

import java.lang.ref.WeakReference;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import static com.zhengyuan.recyclerview.util.ImageUtil.imageUrls;
import static java.lang.Thread.sleep;

/**
 * Created by 林亮 on 2019/1/7
 */

public class LoadNetworkIamgeActivity extends Activity {

    private RecyclerView mRecyclerView;
    private WaterFallAdapter mAaterFallAdapter;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    MyHandle myHandle = new MyHandle(LoadNetworkIamgeActivity.this);
    private List<WaterFallItemEntity> waterFallItemEntities = new ArrayList<WaterFallItemEntity>();

    StaggeredGridLayoutManager staggeredGridLayoutManager = null;
    LinearLayoutManager layoutManager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initListener();
        initData();//假装从服务器获取数据，这里的图片网址写死
        //StaggeredGridLayout();//使用瀑布流布局
        LinearLayout();

    }

    private void LinearLayout() {
        layoutManager = new LinearLayoutManager(LoadNetworkIamgeActivity.this);
        //设置垂直滚动，也可以设置横向滚动
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAaterFallAdapter = new WaterFallAdapter(this, waterFallItemEntities);
        mRecyclerView.setAdapter(mAaterFallAdapter);
    }

    private void StaggeredGridLayout() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAaterFallAdapter = new WaterFallAdapter(this, waterFallItemEntities);
        mRecyclerView.setAdapter(mAaterFallAdapter);
        //mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, 2));//添加分割线
    }


    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);//上下拉刷新加载列表
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
    }

    private void initData() {
        for (int i = 0; i < ImageUtil.imageUrls.length; i++) {
            waterFallItemEntities.add(new WaterFallItemEntity("也许你现在仍然是一个人下班，一个人乘地铁，一个人上楼，一个人吃饭，一个人睡觉，一个人发呆。然而你却能一个人下班，一个人乘地铁，一个人上楼，一个人吃饭，一个人睡觉，一个人发呆。很多人离开另外一个人，就没有自己。而你却一个人，度过了所有。你的孤独，虽败犹荣。"
                    , ImageUtil.imageUrls[i], "2019-1-" + (int) (Math.random() * 10)));
        }
    }

    private void initListener() {
        //初始化上拉刷新
        initPullRefresh();
        //初始化下拉刷新
        initLoadMoreListener();
    }

    /**
     * 上拉刷新
     */
    private void initPullRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //新启动线程加载延时操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //long beginTime = System.currentTimeMillis();//记录开始加载信息时间
                        //模拟加载1秒
                        try {
                            sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //更新数据源，随意添加两张图片
                        //initData();
                        for (int i = 2; i < 4; i++) {
                            waterFallItemEntities.add(0, new WaterFallItemEntity("一个人吃饭，一个人睡觉，一个人发呆。然而你却能一个人下班，一个人乘地铁，一个人上楼，一个人吃饭，一个人睡觉，一个人发呆。很多人离开另外一个人，就没有自己。而你却一个人，度过了所有。你的孤独，虽败犹荣。"
                                    , ImageUtil.imageUrls[i], "2019-1-" + (int) (Math.random() * 10)));
                        }
                        //下面的handle在加载信息完成后调用
                        Message message = new Message();
                        message.what = 200;
                        myHandle.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    /**
     * 通过监听RecycleView下拉到底部，实现下拉刷新
     */
    private void initLoadMoreListener() {
        Log.i("test==", "onRefresh");
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();//
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (/*newState == RecyclerView.SCROLL_STATE_IDLE &&*/ lastVisibleItem + 3 == mAaterFallAdapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<WaterFallItemEntity> footerDatas = new ArrayList<WaterFallItemEntity>();
                            for (int i = 0; i < 2; i++) {
                                WaterFallItemEntity waterFallItemEntity = new WaterFallItemEntity("一个人的生活"
                                        , ImageUtil.imageUrls[i], "2019-1-" + (int) (Math.random() * 10));
                                footerDatas.add(waterFallItemEntity);
                            }
                            mAaterFallAdapter.AddFooterItem(footerDatas);
                            Toast.makeText(LoadNetworkIamgeActivity.this, "更新了 " + footerDatas.size() + " 条目数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 1);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 刷新后更新数据源
     */
    public static class MyHandle extends Handler {
        WeakReference<LoadNetworkIamgeActivity> weakReference = null;

        public MyHandle(LoadNetworkIamgeActivity context) {
            super();
            weakReference = new WeakReference<LoadNetworkIamgeActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference != null && msg != null) {
                LoadNetworkIamgeActivity activity = weakReference.get();
                if (msg.what == 200) {
                    //刷新结束，隐藏刷新动画
                    activity.mSwipeRefreshLayout.setRefreshing(false);
                    activity.mAaterFallAdapter.notifyDataSetChanged();
                    Toast.makeText(activity, "加载成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
