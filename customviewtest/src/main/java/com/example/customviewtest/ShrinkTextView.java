package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class ShrinkTextView extends android.support.v7.widget.AppCompatTextView {

    private int mViewWidth;

    private Matrix matrix;

    private int translate = 0;

    private boolean mAnimation = true;

    private LinearGradient linearGradient;

    public ShrinkTextView(Context context) {
        this(context, null);
    }

    public ShrinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                linearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[] { 0x33ffff00, 0xffffff00, 0x33ffff00}, null, Shader.TileMode.CLAMP);
                getPaint().setShader(linearGradient);
                matrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimation && matrix != null) {
            translate += mViewWidth / 10;
            if (translate > mViewWidth * 2) {
                translate = -mViewWidth;
            }
            matrix.setTranslate(translate, 0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(50);
        }
    }
}
