# RefreshListView

- 用RecycleView实现listview的上拉加载和下拉刷新
- 开发者使用RefreshListView可以对各种控件实现多种下拉刷新效果、上拉加载更多以及配置自定义头部广告位

## 基本用法

1. 添加Gradle依赖
>     dependencies {
>         compile 'com.android.support:recyclerview-v7:latestVersion' 
>     　  compile 'com.nineoldandroids:library:2.4.0'// 记得添加nineoldandroids
>         compile 'cn.bingoogolapple:bga-refreshlayout:latestVersion@aar'
>         }

2. 在布局中添加BGARefreshLayout
>     <cn.bingoogolapple.refreshlayout.BGARefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/refreshLayout"
    style="@style/MatchMatch"
    android:paddingBottom="@dimen/test_padding_margin"
    android:paddingTop="@dimen/test_padding_margin">

    <cn.bingoogolapple.refreshlayout.BGAStickyNavLayout
        style="@style/MatchAuto"
        android:layout_marginBottom="@dimen/test_padding_margin"
        android:layout_marginTop="@dimen/test_padding_margin"
        android:background="@color/test_spacing2"
        android:paddingBottom="@dimen/test_padding_margin"
        android:paddingTop="@dimen/test_padding_margin">

        <cn.bingoogolapple.bgabanner.BGABanner
            android:id="@+id/banner"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            app:banner_indicatorGravity="bottom|right"
            app:banner_placeholderDrawable="@mipmap/holder"
            app:banner_tipTextSize="12sp"
            app:banner_transitionEffect="accordion" />

        <LinearLayout
            style="@style/MatchWrap.Horizontal"
            android:layout_marginBottom="@dimen/test_padding_margin"
            android:layout_marginTop="@dimen/test_padding_margin"
            android:background="@color/colorPrimary"
            android:paddingBottom="5dp"
            android:paddingTop="5dp">

            <TextView
                android:id="@+id/retweet"
                style="@style/AutoWrap"
                android:gravity="center"
                android:text="转发"
                android:textColor="@android:color/white"
                android:textSize="14sp" />

            <TextView
                android:id="@+id/comment"
                style="@style/AutoWrap"
                android:gravity="center"
                android:text="评论"
                android:textColor="@android:color/white"
                android:textSize="14sp" />

            <TextView
                android:id="@+id/praise"
                style="@style/AutoWrap"
                android:gravity="center"
                android:text="赞"
                android:textColor="@android:color/white"
                android:textSize="14sp" />
        </LinearLayout>

        <android.support.v7.widget.RecyclerView
            android:id="@+id/data"
            style="@style/MatchMatch"
            android:layout_marginBottom="@dimen/test_padding_margin"
            android:layout_marginTop="@dimen/test_padding_margin"
            android:background="@android:color/white"
            android:overScrollMode="never"
            android:paddingBottom="@dimen/test_padding_margin"
            android:paddingTop="@dimen/test_padding_margin"
            android:scrollbars="none"
            tools:listitem="@layout/item_normal" />

    </cn.bingoogolapple.refreshlayout.BGAStickyNavLayout>
</cn.bingoogolapple.refreshlayout.BGARefreshLayout>

3. 自定义头部广告位

>        private void initBanner() {
          mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                Glide.with(banner.getContext()).load(model).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) view);
            }
        });

        App.getInstance().getEngine().getBannerModel().enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(R.layout.view_image, bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
            }
        });
    }
  
  4. 下拉刷新和上拉加载
 > 
        @Override
       public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
         mNewPageNumber++;
         if (mNewPageNumber>4){
            refreshLayout.endRefreshing();
            showToast("没有最新数据了");
            return;
        }
        showLoadingDialog();
        //加载数据
        mEngine.loadNewData(mNewPageNumber).enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, final Response<List<RefreshModel>> response) {
                ThreadUtil.runInUIThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.endRefreshing();//先结束再刷新
                            dismissLoadingDialog();
                            mAdapter.addNewData(response.body());
                            mDataRv.smoothScrollToPosition(0);//放在最上面
                    }
                },1000);

            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                refreshLayout.endRefreshing();//先结束再刷新
                dismissLoadingDialog();
            }
        });
    }

       @Override
       public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber>4){
            refreshLayout.endLoadingMore();
            showToast("没有更多数据了");
            return false;
        }
        showLoadingDialog();
        mEngine.loadMoreData(mMorePageNumber).enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, final Response<List<RefreshModel>> response) {
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.endLoadingMore();
                        dismissLoadingDialog();
                        mAdapter.addMoreData(response.body());
                    }
                },2000);

            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                refreshLayout.endLoadingMore();
                dismissLoadingDialog();
            }
        });

        return true;
    }
## 效果图

![](http://ww3.sinaimg.cn/mw690/9df03a21gw1f7j5us153sj20900g1js6.jpg)
![](http://ww2.sinaimg.cn/mw690/9df03a21jw1f7j5nb91xnj208u0g1751.jpg)
