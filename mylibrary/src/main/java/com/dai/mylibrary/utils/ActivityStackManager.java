package com.dai.mylibrary.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityStackManager {

    private static Stack<Activity> activityStack;
    private static ActivityStackManager instance;

    private ActivityStackManager() {
    }

    public static ActivityStackManager getInstance() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    //退出栈顶Activity
    public void popActivity(Activity activity) {
        if (activity != null && activityStack != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    //获得当前栈顶Activity
    public Activity currentActivity() {
        Activity activity = null;
        try {
            activity = activityStack.lastElement();
        } catch (Exception e) {
        }
        return activity;
    }

    //将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    //退出栈中所有Activity
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }
}
