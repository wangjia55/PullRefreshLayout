package com.jacob.pulllayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jacob.pulllayout.layout.StickyNavLayout;

public class MainActivity extends FragmentActivity implements SwipeRefreshLayout.OnRefreshListener{
	private String[] mTitles = new String[] { "简介", "评价", "相关" };
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private StickyNavLayout mStickyNavLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initDatas();
	}


	private void initDatas() {

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return 1;
			}

			@Override
			public Fragment getItem(int position) {
				return ListFragment.newInstance();
			}

		};

		mViewPager.setAdapter(mAdapter);
	}

	private void initViews() {
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
		mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(false);

		mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
		mStickyNavLayout = (StickyNavLayout) findViewById(R.id.sticky_layout);
		mStickyNavLayout.setOnRefreshStateListener(new StickyNavLayout.OnRefreshStateListener() {
			@Override
			public void setRefreshEnable(boolean isEnable) {
				mSwipeRefreshLayout.setEnabled(isEnable);
			}
		});

	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeRefreshLayout.setRefreshing(false);
			}
		},2000);
	}
}
