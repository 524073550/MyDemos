package com.zhuke.yixun.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhuke.comlibrary.base.BaseActivity;
import com.zhuke.comlibrary.base.BaseFragment;
import com.zhuke.yixun.R;
import com.zhuke.yixun.adapter.MainFragmentAdapter;
import com.zhuke.yixun.factory.FragmentFactory;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton mRadio_message;
    private RadioButton mRadio_contacts;
    private RadioButton mRadio_setting;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected View addContent(LayoutInflater layoutInflater, FrameLayout container) {
        return layoutInflater.inflate(R.layout.activity_main, container, true);
    }

    @Override
    protected void initContent(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpage);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        mRadio_message = (RadioButton) view.findViewById(R.id.message);
        mRadio_contacts = (RadioButton) view.findViewById(R.id.contacts);
        mRadio_setting = (RadioButton) view.findViewById(R.id.setting);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
        initViewPage();
    }

    private void initViewPage() {
        mFragments.add(FragmentFactory.getInstance().getFragment(0));
        mFragments.add(FragmentFactory.getInstance().getFragment(1));
        mFragments.add(FragmentFactory.getInstance().getFragment(2));
        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), mFragments));
    }

    @Override
    protected void initTitle() {
        setTitle("消息");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.message:
                mViewPager.setCurrentItem(0);
                rightIV.setVisibility(View.GONE);
                setTitle("消息");
                break;
            case R.id.contacts:
                mViewPager.setCurrentItem(1);
                enableRightImage(R.drawable.add_friend_pressed, this);
                rightIV.setBackgroundResource(R.drawable.add_selector);
                setTitle("联系人");
                break;
            case R.id.setting:
                rightIV.setVisibility(View.GONE);
                setTitle("设置");
                mViewPager.setCurrentItem(2);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0://消息
                mRadioGroup.check(R.id.message);
                setTitle("消息");
                break;
            case 1://联系人
                mRadioGroup.check(R.id.contacts);
                enableRightImage(R.drawable.add_friend_pressed, this);
                rightIV.setBackgroundResource(R.drawable.add_selector);
                setTitle("联系人");
                break;
            case 2://设置
                setTitle("设置");
                mRadioGroup.check(R.id.setting);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.base_iv_right1:
                showToast("添加");
                break;
        }
    }
}
