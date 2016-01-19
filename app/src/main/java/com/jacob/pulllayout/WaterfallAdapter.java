package com.jacob.pulllayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacob.pulllayout.layout.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangqiyu on 2015/12/30.
 */
public class WaterfallAdapter extends RecyclerView.Adapter<WaterfallAdapter.MyViewHolder> {

    LayoutInflater mInflater;
    List<Integer> mLine;

    PullLoadMoreRecyclerView mRecyclerView;
    List<String> vaccineMessages;

    public WaterfallAdapter(Context context, PullLoadMoreRecyclerView mRecyclerView, List<String> vaccineMessages) {
        mInflater = LayoutInflater.from(context);
        mLine = new ArrayList<Integer>();
        if (vaccineMessages == null) {
            this.vaccineMessages = new ArrayList<String>();
        } else {
            this.vaccineMessages = vaccineMessages;
        }
        for (int i = 0; i < 20; i++) {
            mLine.add((int) (1 + Math.random() * 4));
        }
        this.mRecyclerView = mRecyclerView;
        if (this.mRecyclerView != null) {
            //  mRecyclerView.addItemDecoration(new SpaceItemDecoration(6));

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        holder.textViewContent.setMaxLines(mLine.get(position));
        String vaMsg = vaccineMessages.get(position);
        holder.textViewTitle.setText(vaMsg);
    }

    @Override
    public int getItemCount() {
        return vaccineMessages.size();
    }

    /**
     * RecyclerView本身带有ViewHolder，并强制要求使用ViewHolder;  重写ViewHolder
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.text_view_item);
        }
    }

    /**
     * 设置监听
     */

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    /**
     * 设置边距
     */

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) >= 0) {
                outRect.top = space;
                outRect.right = space;
                outRect.left = space;
            }
        }
    }

}
