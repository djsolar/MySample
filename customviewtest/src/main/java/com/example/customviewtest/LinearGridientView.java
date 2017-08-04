package com.example.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class LinearGridientView extends View {

    private LinearGradient linearGradient;

    private Paint paint;

    public LinearGridientView(Context context) {
        super(context);
        linearGradient = new LinearGradient(0, 0, 300, 300, new int[] { Color.YELLOW, Color.GREEN, Color.TRANSPARENT, Color.WHITE}, null, Shader.TileMode.CLAMP);
        paint = new Paint();
    }

    public LinearGridientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(linearGradient);
        canvas.drawCircle(300, 300, 300, paint);
    }
}
