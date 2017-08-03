package com.example.userinterationtest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.FileNotFoundException;

/**
 * Created by zhouyiran on 2017/8/1.
 */

public class Toast {

    private Context mContext;

    private int mDuration;

    private View mNextView;

    private WindowManager windowManager;

    public static final int LENGTH_LONG = 1500;

    public static final int LENGTH_SHORT = 3000;

    public Toast(Context context) {
        mContext = context.getApplicationContext();
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static Toast makeText(Context context, CharSequence message, int duration) {
        Toast result = new Toast(context);
        View view = android.widget.Toast.makeText(context, message, duration).getView();
        if (view != null) {
            TextView tv = view.findViewById(android.R.id.message);
            tv.setText(message);
        }
        result.mNextView = view;
        result.mDuration = duration;
        return result;
    }

    public static Toast makeText(Context context, int resId, int duration) {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    public void show() {
        if (mNextView != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.gravity = Gravity.CENTER|Gravity.CENTER_HORIZONTAL;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    |WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSLUCENT;
            params.y = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, mContext.getResources().getDisplayMetrics());
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            params.windowAnimations = android.R.style.Animation_Toast;
            windowManager.addView(mNextView, params);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mNextView != null) {
                        windowManager.removeView(mNextView);
                        mNextView = null;
                        windowManager = null;
                    }
                }
            }, mDuration);
        }
    }
}
