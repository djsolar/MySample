package com.example.drawabletest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

    private ShapeDrawable[] drawables;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init() {
        setFocusable(true);

        float[] outR = new float[] {24, 24, 24, 24, 0, 0, 0, 0};
        RectF inSet = new RectF(12, 12, 12, 12);
        float[] inR = new float[] {24, 24, 0, 0, 24, 24, 0, 0};
        Path path = new Path();
        path.moveTo(100, 0);
        path.lineTo(0, 100);
        path.lineTo(100, 200);
        path.lineTo(200, 100);

        drawables = new ShapeDrawable[7];
        drawables[0] = new ShapeDrawable(new RectShape());
        drawables[0].getPaint().setColor(Color.parseColor("#ffff0000"));
        drawables[1] = new ShapeDrawable(new OvalShape());
        drawables[1].getPaint().setColor(Color.parseColor("#ff00ff00"));
        drawables[2] = new ShapeDrawable(new RoundRectShape(outR, null, null));
        drawables[2].getPaint().setColor(Color.parseColor("#ff0000ff"));

        drawables[3] = new ShapeDrawable(new RoundRectShape(outR, inSet, null));
        drawables[3].getPaint().setShader(makeSweepShader());

        drawables[4] = new ShapeDrawable(new RoundRectShape(outR, inSet, inR));
        drawables[4].getPaint().setShader(makeLinearShader());

        drawables[5] = new ShapeDrawable(new PathShape(path, 200, 200));
        drawables[5].getPaint().setShader(makeTilingShader());

        drawables[6] = new MyShapeDrawable(new ArcShape(45, -270));
        drawables[6].getPaint().setColor(0x88FF8844);
        MyShapeDrawable msd = (MyShapeDrawable) drawables[6];
        msd.getStrokePaint().setStrokeWidth(4);


        PathEffect pathEffect1 = new DiscretePathEffect(30, 4);
        PathEffect pathEffect2 = new CornerPathEffect(4);
        drawables[3].getPaint().setPathEffect(new ComposePathEffect(pathEffect1, pathEffect2));

    }

    private static Shader makeSweepShader() {
        return new SweepGradient(600, 100, new int[] {0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF0000}, null);
    }

    private static Shader makeLinearShader() {
        return new LinearGradient(0, 0, 50, 50, new int[] {0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF0000}, null, Shader.TileMode.MIRROR);
    }

    private static Shader makeTilingShader() {
        Bitmap bitmap = Bitmap.createBitmap(new int[]{0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0, 0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0, 0xFFFF0000}, 3, 3, Bitmap.Config.ARGB_8888);
        return new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = 30;
        int y = 30;

        int width = 800;
        int height = 100;
        for(Drawable drawable : drawables) {
            drawable.setBounds(x, y, x + width, y + height);
            drawable.draw(canvas);
            y += height + 30;
        }
    }

    private static class MyShapeDrawable extends ShapeDrawable {
        private Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public MyShapeDrawable(Shape s) {
            super(s);
            strokePaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
            shape.draw(canvas, paint);
            shape.draw(canvas, strokePaint);
        }

        public Paint getStrokePaint() {
            return strokePaint;
        }
    }
}
