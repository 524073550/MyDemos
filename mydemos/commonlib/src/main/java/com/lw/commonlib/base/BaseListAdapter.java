package com.lw.commonlib.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseAdapter
 * Created by luwei on 2016/7/10.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected List<T> mData;
    protected Context mContext;
    public BaseListAdapter(Context mContext, List<T> mData){
        this.mContext = mContext;
        this.mData = mData;
    }
    public BaseListAdapter(Context mContext){
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void replaceAll(List<T> newListData, boolean isUpdateAdapter) {
        mData.clear();
        if (newListData != null) {
            mData.addAll(newListData);
        }
        updateAdapter(isUpdateAdapter);
    }

    public void replaceAll(List<T> newListData) {
        replaceAll(newListData, true);
    }

    public void updateAdapter(boolean isUpdateAdapter) {
        if (isUpdateAdapter) {
            notifyDataSetChanged();
        }
    }

    public void append(@NonNull List<T> appendListData, boolean isUpdateAdapter) {
        mData.addAll(appendListData);
        updateAdapter(isUpdateAdapter);
    }

    public List<T> getData() {
        return mData;
    }
}
