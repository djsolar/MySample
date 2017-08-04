package com.example.customviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.audiofx.Visualizer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class SpringProgressView extends View {

    private static final int[] SECTION_COLORS = {0xffffd300,Color.GREEN,0xff319ed4};

    private float maxCount;

    private float currentCount;

    private Paint mPaint;

    private Bitmap bitmap;

    private int mWidth, mHeight;

    public SpringProgressView(Context context) {
        this(context, null);
    }

    public SpringProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            mWidth = widthSize;
        } else {
            mWidth = 0;
        }

        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
        } else {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(null);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        int round = mHeight / 2;
        RectF rectF = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rectF, round, round, mPaint);
        mPaint.setColor(Color.WHITE);
        RectF rectBlackBg = new RectF(2, 2, mWidth - 2, mHeight - 2);
        canvas.drawRoundRect(rectBlackBg, round, round, mPaint);

        float section = currentCount / maxCount;
        RectF rectProgressBg = new RectF(3, 3, section * (mWidth - 3), mHeight);
        if (section <= 1.0f / 3.0f) {
            if (section != 0.0f) {
                mPaint.setColor(SECTION_COLORS[0]);
            } else {
                mPaint.setColor(Color.TRANSPARENT);
            }
        } else {
            int count = (section <= 1.0f / 3.0f * 2) ? 2 : 3;
            int[] colors = new int[count];
            System.arraycopy(SECTION_COLORS, 0, colors, 0, count);
            /*float[] position = new float[count];
            if (count == 2) {
                position[0] = 0.0f;
                position[1] = 1.0f - position[0];
            } else {
                position[0] = 0.0f;
                position[1] = maxCount / 3 / currentCount;
                position[2] = 1.0f - position[0] * 2;
            }
            position[position.length - 1] = 1.0f;*/
            LinearGradient linearGradient = new LinearGradient(3, 3, (mWidth - 3) * section, mHeight - 3, colors, null, Shader.TileMode.MIRROR);
            mPaint.setShader(linearGradient);
        }
        canvas.drawRoundRect(rectProgressBg, round, round, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                move(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                move(x, y);
                break;

            case MotionEvent.ACTION_UP:
                move(x, y);
                break;
        }
        return true;
    }

    private void move(float x, float y) {
        if (x > mWidth)
            return;
        currentCount =  maxCount * (x / mWidth);
        invalidate();
    }

    public float getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }

    public float getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(float currentCount) {
        this.currentCount = currentCount;
    }
}
