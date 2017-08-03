package com.example.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by zhouyiran on 2017/8/1.
 */

public class BatteryReceiver extends BroadcastReceiver {

    private static final String TAG = "BatteryReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        int currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
        int percent = currLevel * 100 / total;
        Log.i(TAG, "percent = " + percent);
    }
}
