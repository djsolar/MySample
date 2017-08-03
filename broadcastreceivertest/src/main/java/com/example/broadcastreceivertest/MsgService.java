package com.example.broadcastreceivertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by zhouyiran on 2017/8/1.
 */

public class MsgService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("msgService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("msgService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
