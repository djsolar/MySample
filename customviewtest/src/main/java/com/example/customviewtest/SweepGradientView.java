package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class SweepGradientView extends View {

    private SweepGradient sweepGradient;

    private Paint mPaint;

    public SweepGradientView(Context context) {
        super(context);
        sweepGradient = new SweepGradient(240, 360, new int[]{Color.CYAN, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.MAGENTA,
                Color.GREEN, Color.TRANSPARENT, Color.BLUE}, null);
        mPaint = new Paint();
        mPaint.setShader(sweepGradient);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(240, 360, 200, mPaint);
    }
}
