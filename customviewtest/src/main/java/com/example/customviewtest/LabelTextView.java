package com.example.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class LabelTextView extends View {

    private String labelText;

    private float labelSize;

    private int labelColor;

    private Paint mPaint;

    private int mAscent;

    public LabelTextView(Context context) {
        this(context, null);
    }

    public LabelTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initLabelView();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LabelTextView);
        CharSequence s = a.getString(R.styleable.LabelTextView_label_text);
        if (s != null) {
            setLabelText(s.toString());
        }
        setLabelColor(a.getColor(R.styleable.LabelTextView_label_color, 0xFF000000));
        int textSize = a.getDimensionPixelSize(R.styleable.LabelTextView_label_size, 0);
        if (textSize > 0) {
            setLabelSize(textSize);
        }
        a.recycle();
    }

    void initLabelView() {
        mPaint = new Paint();
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
        mPaint.setColor(0xFF000000);
        setPadding(3, 3, 3, 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(this.labelText, getPaddingLeft(), -mAscent + getPaddingTop(), mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int textWidth = (int) mPaint.measureText(this.labelText) + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(widthSize, textWidth);
            }
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height;
        mAscent = (int) mPaint.ascent();
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (-mAscent + mPaint.descent()) + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(heightSize, height);
            }
        }
        setMeasuredDimension(width, height);
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        requestLayout();
        invalidate();
    }

    public float getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(float labelSize) {
        this.labelSize = labelSize;
        requestLayout();
        invalidate();
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        invalidate();
    }
}
