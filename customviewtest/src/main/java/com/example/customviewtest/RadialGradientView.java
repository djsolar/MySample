package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class RadialGradientView extends View {

    private RadialGradient radialGradient;

    private Paint mPaint;

    public RadialGradientView(Context context) {
        super(context);

        radialGradient = new RadialGradient(240, 240, 240, new int[] {Color.YELLOW, Color.GREEN, Color.TRANSPARENT, Color.WHITE}, null, Shader.TileMode.REPEAT);
        mPaint = new Paint();
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(240, 360, 200, mPaint);
    }
}
