package com.example.broadcastreceivertest.order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ThirdReceiver extends BroadcastReceiver {


    private static final String TAG = "ThirdReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = getResultExtras(true).getString("msg");
        Log.i(TAG, "msg = " + msg);
    }
}
