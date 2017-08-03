package com.example.userinterationtest;

import android.content.Context;
import android.os.Handler;

/**
 * Created by zhouyiran on 2017/8/1.
 */

public class CustomToast {

    private static android.widget.Toast toast;

    private static Handler handler = new Handler();

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            toast.cancel();
        }
    };

    public static void showToast(Context context, String message, int duration) {
        handler.removeCallbacks(runnable);
        if (toast != null) {
            toast.setText(message);
        } else {
            toast = android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT);
        }
        handler.postDelayed(runnable, duration);
        toast.show();
    }

    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getResources().getString(resId), duration);
    }

}
