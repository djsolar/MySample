package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class ColorPickerView extends View {

    private boolean mTrackingCenter = false;

    private boolean mHighlightCenter = false;

    private Paint mPaint;

    private Paint mCenterPaint;

    private static final int CENTER_X = 200;

    private static final int CENTER_Y = 200;

    private static final int RADIUS = 32;

    private int[] colors;

    private OnColorChangedListener onColorChangedListener;

    public ColorPickerView(Context context) {
        super(context);
    }

    public ColorPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        colors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00,
                0xFFFFFF00, 0xFFFF0000};
        Shader shader = new SweepGradient(0, 0, colors, null);
        mPaint = new Paint();
        mPaint.setShader(shader);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(32);

        mCenterPaint = new Paint();
        mCenterPaint.setColor(Color.RED);
        mCenterPaint.setStrokeWidth(15);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CENTER_X * 2, CENTER_Y * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float r = CENTER_X - mPaint.getStrokeWidth() * 0.5f;
        canvas.translate(CENTER_X, CENTER_Y);
        canvas.drawCircle(0, 0, r, mPaint);
        canvas.drawCircle(0, 0, RADIUS, mCenterPaint);

        if (mTrackingCenter) {
            int c = mCenterPaint.getColor();
            mCenterPaint.setStyle(Paint.Style.STROKE);
            if (mHighlightCenter) {
                mCenterPaint.setAlpha(0xFF);
            } else {
                mCenterPaint.setAlpha(0x80);
            }
            canvas.drawCircle(0, 0, RADIUS + mCenterPaint.getStrokeWidth(), mCenterPaint);
            mCenterPaint.setStyle(Paint.Style.FILL);
            mCenterPaint.setColor(c);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX() - CENTER_X;
        int y = (int) event.getY() - CENTER_Y;
        boolean inCenter = Math.sqrt(x * x + y * y) <= RADIUS;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTrackingCenter = inCenter;
                if (mTrackingCenter) {
                    mHighlightCenter = true;
                    invalidate();
                } else {
                    float angle = (float) Math.atan2(y, x);
                    float unit = (float) (angle / (2.0f * Math.PI));
                    if (unit < 0) {
                        unit += 1;
                    }
                    int color = iterpColor(colors, unit);
                    mCenterPaint.setColor(color);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mTrackingCenter) {
                    if (mHighlightCenter != inCenter) {
                        mHighlightCenter = inCenter;
                        invalidate();
                    }
                } else {
                    float angle = (float) Math.atan2(y, x);
                    float unit = (float) (angle / (2.0f * Math.PI));
                    if (unit < 0) {
                        unit += 1;
                    }
                    int color = iterpColor(colors, unit);
                    mCenterPaint.setColor(color);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mTrackingCenter) {
                    if (inCenter) {
                        if (onColorChangedListener != null) {
                            onColorChangedListener.colorChanged(mCenterPaint.getColor());
                        }
                    }
                    mTrackingCenter = false;
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.onColorChangedListener = onColorChangedListener;
    }

    private int iterpColor(int[] colors, float unit) {

        if (unit <= 0) {
            return colors[0];
        }
        if (unit >= 1) {
            return colors[colors.length - 1];
        }

        float p = unit * (colors.length - 1);
        int i = (int) p;
        p -= i;

        int c0 = colors[i];
        int c1 = colors[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);
        return Color.argb(a, r, g, b);
    }

    private int ave(int c1, int c2, float p) {
        return c1 + Math.round(p * (c2 - c1));
    }

    public interface OnColorChangedListener{
        void colorChanged(int color);
    }
}
