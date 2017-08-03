package com.example.servicetest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

public class RecorderService extends Service {

    private static final String TAG = "RecorderService";

    private TelephonyManager telephonyManager;

    private MediaRecorder recorder;

    public RecorderService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getService();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void getService() {
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void getRecorder() {
        recorder = new MediaRecorder();
        // 设置麦克风
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置输出格式
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 文件保存目录
        recorder.setOutputFile("sdcard/audio.3gp");
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: // 空闲
                    Log.i(TAG, "空闲");
                    if (recorder != null) {
                        recorder.stop();
                        recorder.release();
                        recorder = null;
                    }
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK: // 正在通话
                    Log.i(TAG, "正在通话");
                    if (recorder != null) {
                        recorder.start();
                    }
                    break;

                case TelephonyManager.CALL_STATE_RINGING: // 响铃
                    Log.i(TAG, "响铃");
                    if (recorder == null) {
                        getRecorder();
                    }
                    break;
            }
        }
    }
}
