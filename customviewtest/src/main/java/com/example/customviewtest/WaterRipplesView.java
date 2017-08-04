package com.example.customviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhouyiran on 2017/8/4.
 */

public class WaterRipplesView extends View {

    private BitmapShader bitmapShader;

    private ShapeDrawable shapeDrawable;

    private RadialGradient radialGradient;

    private Paint mPaint;

    private Canvas canvas;

    private Bitmap bitmap;

    public WaterRipplesView(Context context) {
        super(context);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.leaf), dm.widthPixels, dm.heightPixels, true);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        mPaint = new Paint();
    }

    public WaterRipplesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.draw(canvas);

        if (radialGradient != null) {
            mPaint.setShader(radialGradient);
            canvas.drawCircle(0, 0, 2400, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mPaint.setAlpha(400);
        radialGradient = new RadialGradient(event.getX(), event.getY(), 48, new int[] {Color.WHITE, Color.TRANSPARENT}, null, Shader.TileMode.REPEAT);
        postInvalidate();
        return true;
    }
}
