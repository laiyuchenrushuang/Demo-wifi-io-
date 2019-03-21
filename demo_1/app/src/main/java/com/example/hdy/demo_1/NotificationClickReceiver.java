package com.example.hdy.demo_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hdy on 2019/3/21.
 */

public class NotificationClickReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,MainActivity.class);
        context.startActivity(i);
    }
}
