package com.example.hdy.demo_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button mBt;
TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt = findViewById(R.id.button);
        mText = findViewById(R.id.text);
        mBt.setOnClickListener(this);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        mText.setText("world");
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent(ApplictionBroadcastReceiver.ACTION_NOTIFY_MESSAGE);
        intent.setClass(getApplicationContext(), ApplictionBroadcastReceiver.class);
        sendBroadcast(intent);
        super.onStop();
    }
}
