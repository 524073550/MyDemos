package com.zhuke.yixun.factory;

import com.zhuke.comlibrary.base.BaseFragment;
import com.zhuke.yixun.ui.fragment.ContactsFragment;
import com.zhuke.yixun.ui.fragment.MessageFragment;
import com.zhuke.yixun.ui.fragment.SettingFragment;

/**
 * Created by 15653 on 2018/3/26.
 */

public class FragmentFactory {
    private static FragmentFactory mFactory;
    private BaseFragment mBaseFragment;
    private MessageFragment mMessageFragment;
    private ContactsFragment mContactsFragment;
    private SettingFragment mSettingFragment;

    public static FragmentFactory getInstance() {
        if (mFactory == null) {
            synchronized (FragmentFactory.class) {
                if (mFactory == null) {
                    mFactory = new FragmentFactory();
                }
            }
        }
        return mFactory;
    }

    public BaseFragment getFragment(int id) {
        switch (id) {
            case 0:
                return getMessageFragment();
            case 1:
                return getContactsFragment();
            case 2:
                return getSettingFragment();
        }

        return null;
    }

    private BaseFragment getSettingFragment() {
        if (mSettingFragment==null){
            mSettingFragment = new SettingFragment();
        }
        return mSettingFragment;
    }

    private BaseFragment getMessageFragment() {
        if (mMessageFragment==null) {
            mMessageFragment = new MessageFragment();
        }
        return mMessageFragment;
    }

    private BaseFragment getContactsFragment() {
        if (mContactsFragment==null) {
            mContactsFragment = new ContactsFragment();
        }
        return mContactsFragment;
    }
}
