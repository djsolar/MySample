package com.example.servicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MyService";

    Button startService, stopService, bindService, unBindService, startForegroundService, stopForegroundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.startService);
        stopService = (Button) findViewById(R.id.stopService);
        bindService = (Button) findViewById(R.id.bindService);
        unBindService = (Button) findViewById(R.id.unBindService);
        startForegroundService = (Button) findViewById(R.id.startForegroundService);
        stopForegroundService = (Button) findViewById(R.id.stopForegroundService);


        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unBindService.setOnClickListener(this);
        startForegroundService.setOnClickListener(this);
        stopForegroundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.startService:
                startService();
                break;

            case R.id.stopService:
                stopService();
                break;

            case R.id.bindService:
                bindService();
                break;

            case R.id.unBindService:
                unBindService();
                break;

            case R.id.startForegroundService:
                startForegroundService();
                break;

            case R.id.stopForegroundService:
                stopForegroundService();
                break;
        }
    }

    private void startForegroundService() {
        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }

    private void stopForegroundService() {
        Intent intent = new Intent(this, ForegroundService.class);
        stopService(intent);
    }

    private boolean binded = false;

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "服务已连接");
            binded = true;
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            myBinder.greeting("zhouyrian");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "服务已断开");
        }
    };

    private void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if (binded) {
            unbindService(conn);
            binded = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
