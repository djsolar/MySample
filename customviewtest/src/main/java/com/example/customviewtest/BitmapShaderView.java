package com.example.customviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class BitmapShaderView extends View {

    private BitmapShader bitmapShader;

    private ShapeDrawable shapeDrawable;

    private int bitmapWidth;

    private int bitmapHeight;

    private Bitmap bitmap;

    public BitmapShaderView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yangmi);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        shapeDrawable = new ShapeDrawable(new OvalShape());
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(20, 20, bitmapWidth - 100, bitmapHeight);
        shapeDrawable.draw(canvas);
    }
}
