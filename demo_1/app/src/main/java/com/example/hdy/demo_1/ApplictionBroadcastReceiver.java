package com.example.hdy.demo_1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by hdy on 2019/3/21.
 */

public class ApplictionBroadcastReceiver extends BroadcastReceiver{
    private static final String TAG = "DemoBroadcastReceiver";
    public static final String ACTION_NOTIFY_MESSAGE = "com.znq.ACTION_NOTIFY_MESSAGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case ACTION_NOTIFY_MESSAGE:
                Log.d(TAG, "onReceive: 1");
                String name = null;
                String avatar = null;

                //方法一:从AppManger的Stack中拿到当前的Activity
           /*   ActivityManager mActivityManager = ((ActivityManager) context.getApplicationContext()
                        .getSystemService(Context.ACTIVITY_SERVICE));
                List<ActivityManager.RunningTaskInfo> taskInfos = mActivityManager.getRunningTasks(1);
                Class<?> _class= taskInfos.get(0).topActivity.getClass();*/
                //方法二:从AppManger的Stack中拿到当前的Activity
                Activity activity = AppManager.getInstance().currentActivity();
                if(activity==null){
                    Log.d(TAG, "onReceive: _class activity isnull");
                    return;
                }
                Class<?> _class = activity.getClass();
                Log.d(TAG, "onReceive: _class ="+_class);
                if (_class != null) {
                    if (null != reflectionActivity()) {
                        //拿到反射的数据
                        Bundle _bundle = reflectionActivity();
                        if (_bundle != null) {
                            name = _bundle.getString("name");
                            avatar = _bundle.getString("avatar");
                        }
                    }

                    NotificationUtils _notifyUtils = NotificationUtils.getInstance();
                    _notifyUtils.init(context.getApplicationContext(), _class);
                    _notifyUtils.showNotification("111111111");
        }
                break;
    }
}

    private Bundle reflectionActivity() {
        String workerClassName = AppManager.getInstance().currentActivity().getClass().getName();
        Bundle result = null;
        try {
            Class workerClass = Class.forName(workerClassName);
            //反射出该Class类中的onBackActivity()方法
            Method _method = workerClass.getDeclaredMethod("onBackActivity");
            //取消访问私有方法的合法性检查
            _method.setAccessible(true);
            //调用onBackActivity()方法
            Object oo = AppManager.getInstance().currentActivity();
            result = (Bundle) _method.invoke(oo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    }
