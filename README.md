# RecycleView
RecycleView+CardView+Glide+Swip+swiperefresh实现图片的动态加载，刷新

RecyclerView是support:recyclerview-v7中提供的控件，新添加的一个用来取代ListView。RecyclerView已经标准化ViewHolder，我们自定义的ViewHoler需要继承 RecyclerView.ViewHolder，然后在构造方法中初始化控件。RecyclerView和好处很多，例如缓存，预加载，相同item避免重复刷新。好处就不讲了。


第一个部分介绍使用RecyclerView实现三种布局形式，LinearLayoutManager （线性布局，分水平和垂直两种），GridLayoutManager （网格布局），StaggeredGridLayoutManager（流布局）。这里只是使用本地图片的显示

第二部分介绍使用RecyclerView+Glide+CardView实现动态加载网络图片，实现类似新闻app的新闻查看效果。并使用CardView美化效果。

第三部分介绍RecyclerView+Glide+CardView+SwipeRefreshLayout实现上下拉刷新列表。

第一个部分
一、RecyclerView实现三种布局
效果：（照片有点歪，能看就行哈哈哈，别问我问什么不用截图，第三种属于网络图片的加载直接贴上吧）



1、添加依赖

    compile 'com.android.support:cardview-v7:28.0.0'  // 这是cardView的依赖

    implementation 'com.android.support:recyclerview-v7:28.0.0'

    compile 'com.github.bumptech.glide:glide:3.5.2'
2、添加网络权限，因为我们待会要从网络上加载图片

    <uses-permission android:name="android.permission.INTERNET" />
3、编写布局

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_list"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="bottom">

        <Button
            android:id="@+id/btn_linearlayout"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="线性(垂直)" />

        <Button
            android:id="@+id/btn_linearlayout_horizatol"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="线性(水平)" />

        <Button
            android:id="@+id/btn_grid"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="网格布局" />

        <Button
            android:id="@+id/btn"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="瀑布流" />

        <Button
            android:id="@+id/btn_load_network_image"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="网络图片" />
    </LinearLayout>
</LinearLayout>
4、有了android.support.v7.widget.RecyclerView组件后，我们还要定义item的样式，这里只有一张图片和一句话。recycleview_item.xml

<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cv_item"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    android:foreground="?android:attr/selectableItemBackground"
    card_view:cardBackgroundColor="#FFFFFF"
    card_view:cardCornerRadius="4dp"
    card_view:cardElevation="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/iv_pic"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_weight="1"/>

        <TextView
            android:id="@+id/tv_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="20dp"
            android:textColor="#000000" />
    </LinearLayout>

</android.support.v7.widget.CardView>
item的样式最外面是一个CardView组件，会比我们自定义组件漂亮好多。CardView如何使用这么不再讲述。

5、有啦android.support.v7.widget.RecyclerView组件和item后，我们在代码中为其添加内容。

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mButtonLinearLayout;
    private Button mButtonLinearLayoutHorizatol;
    private Button mButtonGrid;
    private Button mButton;
    private Button mLoadNetImage;
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
        mButtonLinearLayout.setOnClickListener(this);
        mButtonGrid.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mButtonLinearLayoutHorizatol.setOnClickListener(this);
        mLoadNetImage.setOnClickListener(this);

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
                Intent intent = new Intent();
                intent.setClass(this, LoadNetworkIamgeActivity.class);
                startActivity(intent);
                break;

        }
    }
}
代码很简单，然后我们需要为RecycleView添加一个适配器，并且这几种布局都是使用这一个适配器，是不是很强大，很牛p。

6，适配器RecyclerViewAdapter 

/**
 * Created by 林亮 on 2019/1/7
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NormalViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mTitle;
    private int[] mPic;

    //通过构造方法将图片以及文字，上下文传递过去
    public RecyclerViewAdapter(Context context, String[] title, int[] pic) {
        mContext = context;
        mTitle = title;
        mPic = pic;
        mLayoutInflater = LayoutInflater.from(context);

    }

    //我们创建一个ViewHolder并返回，ViewHolder必须有一个带有View的构造函数，这个View就是我们Item的根布局，在这里我们使用自定义Item的布局；
    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.recycleview_item, parent, false));
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(NormalViewHolder holder, final int position) {
        holder.mTextView.setText(mTitle[position]);
        holder.mImageView.setBackgroundResource(mPic[position]);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return mTitle == null ? 0 : mTitle.length;
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        CardView mCardView;
        ImageView mImageView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }

}
其中NormalViewHolder 继承自RecyclerView.ViewHolder，比之前使用ListView简单多了。over。第一部分完成，如果使用过listView这里的代码就应该来说很简单了。但是我们在使用过程中基本都是从网络上加载图片。于是有了第二部分

第二部分、介绍使用RecyclerView+Glide加载网络图片
1、其实使用Glide加载网络图片的过程很简单之前加载本地图片的时候我们使用

holder.mImageView.setBackgroundResource(mPic[position]);
就是显示了本地图片，而使用网络图片也是一句话（在之前添加过依赖）

//用glide加载网络图片 并放入imageview中 
        Glide.with(mContext).load(mItemEntitys.get(position).getmImageUri()).into(holder.mImageview);
.with()的参数是上下文，.load()中的参数是图片的Uri,.into()的参数就是显示图片的ImageView了。

为了完整展示实现效果，这里还是将代码贴出来，因为有些细节还是不一样的。

2、编写item布局，item_water_fall.xml

<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:cardView="http://schemas.android.com/tools"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cv_item"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="3dp"
    android:layout_marginLeft="6dp"
    android:layout_marginRight="6dp"
    android:layout_marginTop="3dp"
    android:orientation="vertical"
    android:padding="10dp"
    card_view:cardCornerRadius="4dp"
    card_view:cardElevation="5dp">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="5dp"
            android:singleLine="true"
            android:text="你的孤独，虽败犹荣。"
            android:textColor="#000000"
            android:textSize="13sp" />

        <ImageView
            android:id="@+id/image"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_below="@+id/title"
            android:layout_weight="1" />

        <TextView
            android:id="@+id/time"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignBottom="@+id/image"
            android:layout_alignRight="@+id/image"
            android:layout_marginBottom="10dp"
            android:layout_marginRight="10dp"
            android:text="2019-1-15"
            android:textColor="#000000"
            android:textSize="12sp" />
    </RelativeLayout>
</android.support.v7.widget.CardView>
3、加载网络图片的activity


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
        initData();//假装从服务器获取数据，不过这里的图片确实是网址

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
其中的WaterFallItemEntity是定义的一个POJO对象，承载item对内容

4、WaterFallItemEntity:

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
5、最后就是适配器了

//瀑布流适配器
public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    private Context mContext;
    private List<WaterFallItemEntity> mItemEntitys;

    public WaterFallAdapter(Context content, List<WaterFallItemEntity> itemEntitys) {
        this.mContext = content;
        this.mItemEntitys = itemEntitys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取item_layout的布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_water_fall, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitle.setText(mItemEntitys.get(position).getmTitle());
        //用glide加载网络图片 并放入imageview中
        Glide.with(mContext).load(mItemEntitys.get(position).getmImageUri()).into(holder.mImageview);
        holder.mTime.setText(mItemEntitys.get(position).getmTime());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + position + "个卡片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置图片的点击事件
    private void setItemListener(final ViewHolder holder, final int position, final String url) {
        //如果holder为空 return；
        if (holder == null) {
            return;
        }
        //每个图片的点击事件
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示大图功能以后再说
                Toast.makeText(mContext, ">>>", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemEntitys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTitle;
        ImageView mImageview;
        TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mCardView = (CardView) view.findViewById(R.id.cv_item);
            mTitle = (TextView) view.findViewById(R.id.title);
            mImageview = (ImageView) view.findViewById(R.id.image);
            mTime = (TextView) view.findViewById(R.id.time);
        }
    }

}
8、关于recyclerview 分割线需要自己实现，可以实现不同的样式，具体怎么实现这么不讲述，大家可以参考

https://blog.csdn.net/yancychas/article/details/77484659
