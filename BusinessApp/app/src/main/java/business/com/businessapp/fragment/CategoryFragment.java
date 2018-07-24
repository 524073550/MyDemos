package business.com.businessapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import business.com.businessapp.R;
import business.com.businessapp.base.BaseFragment;
import business.com.businessapp.news.beans.NewsBean;
import business.com.businessapp.news.presenter.NewsPresenter;
import business.com.businessapp.news.view.INewsView;

/**
 * Created by decheng.yang on 2018/2/5.
 */

public class CategoryFragment extends  BaseFragment<INewsView, NewsPresenter> implements INewsView {
    private View view;

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);

        super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void addNews(List<NewsBean> newsList) {

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
}
