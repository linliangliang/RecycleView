package com.zhengyuan.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.zhengyuan.recyclerview.atapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mButtonLinearLayout;
    private Button mButtonLinearLayoutHorizatol;
    private Button mButtonGrid;
    private Button mButton;
    private Button mLoadNetImage;
    private Button mButtonDiffItems;
    //item 显示所需
    private String[] title = {"你的脸上云淡风轻，谁也不知道你的牙咬得有多紧",
            "你走路带着风，谁也不知道你膝盖上仍有曾摔伤的淤青",
            "你笑得没心没肺，没人知道你哭起来只能无声落泪。",
            "要让人觉得毫不费力，只能背后极其努力。",
            "我们没有改变不了的未来，只有不想改变的过去。",
            " 过去已然存在，不是不想改变，是不能改变。",
            "我们没有改变不了的未来，只有改变不了的过去。"
    };
    private int[] pic = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mButtonLinearLayout = (Button) findViewById(R.id.btn_linearlayout);
        mButtonLinearLayoutHorizatol = (Button) findViewById(R.id.btn_linearlayout_horizatol);
        mButtonGrid = (Button) findViewById(R.id.btn_grid);
        mButton = (Button) findViewById(R.id.btn);
        mLoadNetImage = findViewById(R.id.btn_load_network_image);
        mButtonDiffItems = findViewById(R.id.btn_diff_items);
        mButtonLinearLayout.setOnClickListener(this);
        mButtonGrid.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mButtonLinearLayoutHorizatol.setOnClickListener(this);
        mLoadNetImage.setOnClickListener(this);
        mButtonDiffItems.setOnClickListener(this);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置垂直滚动，也可以设置横向滚动
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //RecyclerView设置Adapter
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this, title, pic));

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_linearlayout:
                // 创建一个线性布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                //设置垂直滚动，也可以设置横向滚动
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(new RecyclerViewAdapter(this, title, pic));
                break;
            case R.id.btn_linearlayout_horizatol:
                // 创建一个线性布局管理器
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
                //设置垂直滚动，也可以设置横向滚动
                layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(layoutManager1);
                mRecyclerView.setAdapter(new RecyclerViewAdapter(this, title, pic));
                break;
            case R.id.btn_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //Grid视图
                mRecyclerView.setAdapter(new RecyclerViewAdapter(this, title, pic));
                break;

            case R.id.btn:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                mRecyclerView.setAdapter(new RecyclerViewAdapter(this, title, pic));
                break;
            case R.id.btn_load_network_image:
                intent = new Intent();
                intent.setClass(this, LoadNetworkIamgeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_diff_items:
                intent = new Intent();
                intent.setClass(this, LoadDiffItemsActivity.class);
                startActivity(intent);
                break;

        }
    }
}
