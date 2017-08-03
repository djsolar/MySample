package com.example.drawabletest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zhouyiran on 2017/7/26.
 */

public class CustomView extends View {

    private static final int TYPE_CIRCLE = 0;

    private static final int TYPE_ROUND = 1;

    private static final int DEFAULT_BORDER_VALUE = 10;

    private int type;

    private int borderRadius;

    private Bitmap mSrc;

    private int mWidth;

    private int mHeight;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);

        type = typedArray.getInt(R.styleable.CustomView_type, TYPE_CIRCLE);
        borderRadius = typedArray.getDimensionPixelSize(R.styleable.CustomView_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_BORDER_VALUE, getResources().getDisplayMetrics()));
        mSrc = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.CustomView_src, 0));

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (specWidthMode == MeasureSpec.EXACTLY) {
            mWidth = specWidth;
        } else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
            if (specWidthMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(specWidth, desireByImg);
            } else {
                mWidth = desireByImg;
            }
        }

        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (specHeightMode == MeasureSpec.EXACTLY) {
            mHeight = specHeight;
        } else {
            int desireByImg = getPaddingTop() + getPaddingBottom() + mSrc.getHeight();
            if (specHeightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(specHeight, desireByImg);
            } else {
                mHeight = desireByImg;
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (type == TYPE_CIRCLE) {

            int width = Math.min(mWidth, mHeight);
            mSrc = mSrc.createScaledBitmap(mSrc, width, width, false);
            canvas.drawBitmap(createCircleBitmap(mSrc, width), 0, 0, null);
        } else if (type == TYPE_ROUND) {
            canvas.drawBitmap(createRoundBitmap(mSrc), 0, 0, null);
        }
    }

    private Bitmap createRoundBitmap(Bitmap mSrc) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRoundRect(new RectF(0, 0, mWidth, mHeight), borderRadius, borderRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc, 0, 0, paint);
        return bitmap;
    }

    private Bitmap createCircleBitmap(Bitmap mSrc, int width) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc, 0, 0, paint);
        return bitmap;
    }
}
