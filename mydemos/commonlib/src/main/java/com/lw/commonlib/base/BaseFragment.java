package com.lw.commonlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.lw.commonlib.base.utils.volly.RequestManager;

/**
 * BaseFragment
 * Created by luwei on 2016/7/10.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    private View mSavedView;
    private boolean hasAnnotationField;
    protected RequestManager mRequestManager;
    protected Object mVolleyTag;
    protected int PAGE_SIZE = 10;
    protected int mPageIndex = 1;

    /**
     * 获取保存的Fragment的view
     */
    protected View getSavedView() {
        if (mSavedView != null) { // 保存一个fragment实例的View的状态
            ViewParent parent = mSavedView.getParent();
            if (parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(mSavedView);
        }
        return mSavedView;
    }

    /**
     * fragment带参数的实例化
     *
     * @param type
     * @param bundle
     * @return
     */
    public static BaseFragment newInstanceWithArgs(Class<? extends BaseFragment> type, Bundle bundle) {
        try {
            BaseFragment fragment = type.newInstance();
            if (bundle != null) {
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestManager = RequestManager.getInstance(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (hasAnnotationField && outState != null) {
            outState.putAll(getArguments());
        }
    }

    /**
     * 设置保存的Fragment的view
     */
    protected void setSavedView(View view) {
        FrameLayout containerView = new FrameLayout(getActivity());
        containerView.addView(view);
        mSavedView = containerView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mSavedView;
    }

    @Override
    public void onDestroy() {
        mSavedView = null;
        super.onDestroy();
        cancelRequestQueue();
    }

    @Override
    public void onDetach() {
        mSavedView = null;
        super.onDetach();
    }

    protected Object getVolleyTag() {
        if (mVolleyTag == null) {
            mVolleyTag = String.valueOf(hashCode());
        }
        return mVolleyTag;
    }

    protected void addRequestQueue(Request request) {
        mRequestManager.addToRequestQueue(request, getVolleyTag());
    }

    protected void cancelRequestQueue() {
        mRequestManager.cancel(getVolleyTag());
    }

    protected void addRequestQueue(Request request, Object tag) {
        mRequestManager.addToRequestQueue(request, tag);
    }

    protected void delRequestQueue(Object tag) {
        mRequestManager.cancel(tag);
    }

    protected void showToast(String text) {
        Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(isFastClick()){
            return;
        }
    }

    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long result = time - lastClickTime;
        if (result < 500 && result > 0) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
