package com.zhengyuan.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhengyuan.recyclerview.entity.IData;
import com.zhengyuan.recyclerview.entity.Info1;
import com.zhengyuan.recyclerview.entity.Info2;
import com.zhengyuan.recyclerview.loadNetImageActivityAdapter.DiffItemsAdapter;
import com.zhengyuan.recyclerview.loadNetImageActivityAdapter.WaterFallAdapter;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 林亮 on 2019/1/15
 */

public class LoadDiffItemsActivity extends Activity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager layoutManager = null;
    private DiffItemsAdapter mDiffItemsAdapter;

    private List<IData> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_diffitems);
        initData();
        init();
    }

    @Override
    public void onClick(View v) {

    }

    private void init() {
        mRecyclerView = findViewById(R.id.RecyclerView);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mDiffItemsAdapter = new DiffItemsAdapter(this, datas);
        mRecyclerView.setAdapter(mDiffItemsAdapter);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Info1 info1 = new Info1();
            info1.setLeft_icon_id(R.mipmap.ic_launcher);
            info1.setmContent("党的十八大以来，以习近平同志为核心的党中央坚定推进全面从严治党，党内政治生活展现新气象，党内政治生态明显好转。但“全面从严治党永远在路上”，从巡视监督、日常监督发现的问题看，一些领导干部贯彻新形势下党内政治生活若干准则还存在温差、落差、偏差。比如，落实“两个维护”，空喊口号，不用心、不务实、不尽力，搞形式主义、虚于应付；比如，贯彻党中央决策部署，依然存在打折扣、搞变通、做选择的情况，有的甚至敷衍了事、一拖再拖；比如，对打着领导干部旗号的违规干预行为，不讲原则、主动投机，甚至慷公家之慨向领导干部家属亲友输送利益。凡此种种无不表明，严肃党内政治生活，最重要的是知行合一；全面从严治党，最根本的是令行禁止。");
            info1.setmImagedesc("三论学习贯彻习近平十九届中纪委三次全会讲话精神");
            info1.setmTime("2019-1-10");
            datas.add(info1);
            Info1 info2 = new Info1();
            info2.setLeft_icon_id(R.mipmap.ic_launcher);
            info2.setmContent("“榜样是看得见的哲理”。习近平总书记反复强调，要把我们党建设好，必须抓住“关键少数”。贯彻新形势下党内政治生活若干准则，坚定不移推进全面从严治党，领导干部特别是高级干部具有关键作用。职位越高越要自觉按照党提出的标准严格要求自己，越要以坚强党性和高尚品格，为全党带好头、作表率。惟其如此，才能在层层示范、层层带动中，形成管党治党、从严治党的强大势能；才能在深学笃行、知行合一中，彰显中国共产党人的真理力量和人格力量。");
            info2.setmImagedesc("知者行之始，行者知之成");
            info2.setmTime("2019-1-14");
            datas.add(info2);
            Info2 info3 = new Info2();
            info3.setTitle("插播一条广告，哈哈哈哈哈哈哈");
            datas.add(info3);
        }

        /*Info1 info1 = new Info1();
        info1.setmContent("种类1");
        info1.setmImagedesc("右边");
        info1.setmTime("2019-1-18");
        info1.setLeft_icon_id(R.mipmap.ic_launcher);


        Info1 info3 = new Info1();
        info3.setCenter_str("种类2");
        info3.setRight_str("右边2");
        info3.setLeft_icon_id(R.mipmap.ic_launcher);

        Info2 info4 = new Info2();
        info4.setTitle("卡片2");

        Info1 info5 = new Info1();
        info5.setCenter_str("种类2");
        info5.setRight_str("右边2");
        info5.setLeft_icon_id(R.mipmap.ic_launcher);

        *//*Info3 info3=new Info3();
        info3.setCenter_str("3");
        info3.setRight_str("右边");
        info3.setLeft_icon_id(R.mipmap.ic_launcher);*//*

        datas.add(info1);
        datas.add(info2);
        datas.add(info3);
        datas.add(info4);
        datas.add(info5);*/
        /*datas.add(info3);*/
    }
}
