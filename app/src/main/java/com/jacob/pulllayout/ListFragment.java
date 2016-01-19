package com.jacob.pulllayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacob.pulllayout.layout.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjia on 2016/1/19.
 */
public class ListFragment extends Fragment {
    private PullLoadMoreRecyclerView mPullRecyclerView;
    private WaterfallAdapter mAdapter;
    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       final List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("This is a test " + i);
        }
        mAdapter = new WaterfallAdapter(getContext(), mPullRecyclerView, list);
        mPullRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pull_recycler);
        mPullRecyclerView.setAdapter(mAdapter);
        mPullRecyclerView.setStaggeredGridLayout(1);
        mPullRecyclerView.setHasMore(false);
        mPullRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRecyclerView.setPullLoadMoreCompleted();
                        list.add("add more");
                        list.add("add more");
                        mAdapter.notifyDataSetChanged();
                    }
                },2000);
            }
        });

    }
}
