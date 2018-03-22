package com.zhuke.rxutils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by 15653 on 2018/3/21.
 */

class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Integer> mIntegers;


    public MyAdapter(Context context, List<Integer> integers) {
        this.mContext = context;
        this.mIntegers = integers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.time_iteam, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.mTv.start(mIntegers.get(position));
    }

    @Override
    public int getItemCount() {
        return mIntegers == null ? 0:mIntegers.size();
    }

    public void refreshData(List<Integer> integers) {
        if (mIntegers!=null) {
            mIntegers.addAll(integers);
            notifyDataSetChanged();
        }
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private final CountdownView mTv;
        public MyHolder(View itemView) {
            super(itemView);
            mTv = (CountdownView) itemView.findViewById(R.id.tv);
        }
    }
}
