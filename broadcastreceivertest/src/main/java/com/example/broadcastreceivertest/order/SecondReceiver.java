package com.example.broadcastreceivertest.order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondReceiver extends BroadcastReceiver {

    private static final String TAG = "SecondReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = getResultExtras(true).getString("msg");
        Log.i(TAG, "msg = " + message);

        Bundle bundle = new Bundle();
        bundle.putString("msg", message + "@SecondReceiver");
        setResultExtras(bundle);
    }
}
