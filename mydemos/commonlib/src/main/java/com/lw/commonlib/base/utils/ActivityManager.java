package com.lw.commonlib.base.utils;

import android.app.Activity;
import android.content.Intent;

import com.apkfuns.logutils.LogUtils;

import java.util.List;
import java.util.Stack;

/**
 * 管理所有Activity 当启动一个Activity时，就将其保存到Stack中， 退出时，从Stack中删除
 */
public class ActivityManager {
    /**
     * 保存所有Activity
     */
    private volatile Stack<Activity> activityStack = new Stack<>();

    private static volatile ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 创建单例类，提供静态方法调用
     *
     * @return ActivityManager
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 退出Activity
     *
     * @param activity Activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            finishActivity(activity);
        }
    }

    /**
     * 退出Activity
     */
    public void popActivity(Class cls) {
        if (null == cls) {
            LogUtils.e("cls is null");
            return;
        }

        for (Activity activity : activityStack) {
            if (null == activity || activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
        printAllActivity();
    }

    public void popActivityAll(Class cls) {
        if (null == cls) {
            LogUtils.e("cls is null");
            return;
        }

        int index;
        while ((index = activityStack.search(cls)) != -1) {
            Activity activity = activityStack.get(index);
            if (null != activity && activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    private void printAllActivity() {
//        for (Activity activity : activityStack) {
//            LogUtils.e("activity:" + activity.getClass().getSimpleName());
//        }
//        LogUtils.e("---------------------------------------------\n");
    }

    /**
     * 获得当前栈顶的Activity
     *
     * @return Activity Activity
     */
    public Activity currentActivity() {
        Activity activity = null;
        while (!activityStack.empty()) {
            activity = activityStack.lastElement();
            if (activity.isFinishing()) {
                popActivity(activity);
            } else {
                break;
            }
        }
        return activity;
    }

    public boolean isCurrentActivity(Class cls) {
        Activity currentActivity = currentActivity();
        if (currentActivity == null) {
            return false;
        } else {
            return currentActivity.getClass().getName().equals(cls.getName());
        }
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity Activity
     */
    public void pushActivity(Activity activity) {
        activityStack.add(activity);
        printAllActivity();
    }

    /**
     * 退出栈中其他所有Activity
     *
     * @param cls Class 类名
     */
    public void popOtherActivity(Class cls) {
        if (null == cls) {
            LogUtils.e("cls is null");
            return;
        }

        for (Activity activity : activityStack) {
            if (null == activity || activity.getClass().equals(cls)) {
                continue;
            }
            finishActivity(activity);
        }
    }

    public void popOtherActivity(List<Class> cls) {
        if (null == cls) {
            LogUtils.e("cls is null");
            return;
        }
        for (Activity activity : activityStack) {
            if(activity == null){
                continue;
            }
            boolean flag = false;
            for(Class clz : cls){
                if (activity.getClass().equals(clz)) {
                    flag = true;
                    break;
                }
            }
            if(!flag){
                finishActivity(activity);
            }
        }
    }

    public boolean hasActivity(Class cls) {
        if (null == cls) {
            LogUtils.e("cls is null");
            return false;
        }

        for (Activity activity : activityStack) {
            if (null == activity || activity.getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }

    private void finishActivity(Activity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 退出栈中所有Activity
     */
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            finishActivity(activity);
            popActivity(activity);
        }
    }

    public void startNextActivity(Class<?> activity) {
        Activity curActivity = currentActivity();
        Intent intent = new Intent(curActivity, activity);
        curActivity.startActivity(intent);
//        curActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }


}
