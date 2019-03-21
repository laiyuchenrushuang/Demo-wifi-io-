package com.example.hdy.demo_1;

import android.app.Activity;

import java.util.List;
import java.util.Stack;

/**
 * Created by hdy on 2019/3/21.
 */

class AppManager {
    private static AppManager instance;
    private static Stack<Activity> activityStack;

    public static AppManager getInstance() {
        if (instance == null) {
            //懒汉双层锁,保证线程安全
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到stack中
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
        activityStack.add(activity);
    }

    /**
     * 获取stack中当前的Activity
     */
    public Activity currentActivity() {
        if (null != activityStack && activityStack.size()!=0&&null != activityStack.lastElement()) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 移除当前的Activity
     */
    public void finishActivity() {
        if (null != activityStack && null != activityStack.lastElement()) {
            finishActivity(activityStack.lastElement());
        }
    }

    /**
     * 移除指定的Activity
     *
     * @param activity 指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除指定Class所对应的Activity
     */
    public void finishActivity(Class<?> cls) {
        /*Stack<Activity> activitys = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activitys.add(activity);
            }
        }

        for (Activity activity : activitys) {
            finishActivity(activity);
        }*/
        for (int i = 0; i <activityStack.size() ; i++) {
            Activity activity = activityStack.get(i);
            if(activity.getClass().equals(cls)){
                activity.finish();
                activityStack.remove(i);
            }
        }
    }

    /**
     * 移除所有的Activity
     */
    public void finishAllActivity() {
        if (activityStack == null)
            return;

        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    public void retainActivity(List<Class<?>> list){

        for (int i = 0; i <activityStack.size() ; i++) {
            Activity activity = activityStack.get(i);
            if(!list.contains(activity.getClass())){
                activityStack.remove(i);
                activity.finish();
                activity=null;
            }
        }

    }
    public int getActivityNumber(){
        return activityStack.size();
    }

}
