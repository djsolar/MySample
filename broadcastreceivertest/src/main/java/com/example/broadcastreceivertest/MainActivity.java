package com.example.broadcastreceivertest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BatteryReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.My_BROADCAST");
                intent.putExtra("msg", "msg");
                sendOrderedBroadcast(intent, "android.permission.MY_BROADCAST_PERMISSION");
            }
        });

       /* Intent intent = getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int totalLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
        int percent = currLevel * 100 / totalLevel;
        Log.i(TAG, "percent = " + percent);*/
    }
}
