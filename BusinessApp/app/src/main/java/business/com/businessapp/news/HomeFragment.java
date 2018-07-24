package business.com.businessapp.news;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import business.com.businessapp.R;
import business.com.businessapp.base.BaseFragment;
import business.com.businessapp.base.mvp.factory.CreatePresenter;
import business.com.businessapp.news.adapter.NewsAdapter;
import business.com.businessapp.news.beans.NewsBean;
import business.com.businessapp.news.presenter.NewsPresenter;
import business.com.businessapp.news.view.INewsView;
import business.com.businessapp.view.swipetoloadlayout.base.OnLoadMoreListener;
import business.com.businessapp.view.swipetoloadlayout.base.OnRefreshListener;
import business.com.businessapp.view.swipetoloadlayout.base.SwipeToLoadLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by decheng.yang on 2018/2/5.
 */

@CreatePresenter(NewsPresenter.class)
public class HomeFragment extends BaseFragment<INewsView, NewsPresenter> implements INewsView,OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.refresh)
    SwipeToLoadLayout mLoadLayout;
    @Bind(R.id.swipe_target)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mData = new ArrayList<NewsBean>();
    ;
    private int pageIndex = 0;

    //private View view;
    //private NewsPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

       /* mPresenter = new NewsPresenter();
        mPresenter.attachView(this);
        mPresenter.subscribe();
*/
        Log.d("Fragment", "HomeFragment->onCreateView");

        return rootView;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getActivity().getApplicationContext(), mData);
        mRecyclerView.setAdapter(mAdapter);

        mLoadLayout.setOnRefreshListener(this);
        mLoadLayout.setOnLoadMoreListener(this);

    }

    @Override
    protected void setData() {
        //mPresenter.loadNews(0, 0);
        getMvpPresenter().loadNews(0, 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.loadNews(0, 0);
        //getMvpPresenter().loadNews(0, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unsubscribe();
        }*/

        ButterKnife.unbind(this);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        onComplete(mLoadLayout);
        mData.addAll(newsList);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String msg, String content) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onRefresh() {
        getMvpPresenter().loadNews(0, 0);
    }

    @Override
    public void onLoadMore() {
        getMvpPresenter().loadNews(0, 0);
    }
}
