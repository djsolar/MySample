package com.example.drawabletest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by zhouyiran on 2017/7/26.
 */

public class RoundImageView extends AppCompatImageView {

    private static final int TYPE_CIRCLE = 0;

    private static final int TYPE_ROUND = 1;

    private static final int DEFAULT_BORDER_RADIUS = 10;

    private int type;

    private int mBorderRadius;

    private Matrix mMatrix;

    private Paint bitmapPaint;

    private BitmapShader bitmapShader;

    private int radius;

    private int mWidth;

    private RectF roundRectF;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mMatrix = new Matrix();
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mBorderRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_BORDER_RADIUS, getResources().getDisplayMetrics()));
        type = typedArray.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            radius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null)
            return;
        setUpShader();
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(roundRectF, mBorderRadius, mBorderRadius, bitmapPaint);
        } else {
            canvas.drawCircle(radius, radius, radius, bitmapPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (type == TYPE_ROUND) {
            roundRectF = new RectF(0, 0, getWidth(), getHeight());
        }
    }

    private static final String STATE_INSTANCE = "state_instance";

    private static final String STATE_TYPE = "state_type";

    private static final String STATE_BORDER_RADIUS = "border_radius";


    @Override
    protected Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
            type = bundle.getInt(STATE_TYPE);
            mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    public void setType(int type) {
        if (this.type != type) {
            if(type != TYPE_CIRCLE && type != TYPE_ROUND) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }
    }

    public void setBorderRadius(int mBorderRadius) {
        int dpValue = dp2px(mBorderRadius);
        if (dpValue != this.mBorderRadius) {
            this.mBorderRadius = dpValue;
            invalidate();
        }
    }

    private int dp2px(int mBorderRadius) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mBorderRadius, getResources().getDisplayMetrics());
    }

    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null)
            return;

        Bitmap bitmap = drawableToBitmap(drawable);

        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mWidth * 1.0f / bSize;

        } else if (type == TYPE_ROUND) {
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
        }
        mMatrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(mMatrix);
        bitmapPaint.setShader(bitmapShader);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
