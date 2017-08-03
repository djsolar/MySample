package com.example.spannablestringtest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        // showForegroundColor();
        // showBackgroundColor();
        // showRelativeFont();
        // showStrikethroughLine();
        // showSuperAndSub();
        // showStyleFont();
        // showImage();
        showClickFont();
    }

    // 改变前景色
    private void showForegroundColor() {

        SpannableString spannableString = new SpannableString("设置文字的前景色是红色");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FFFF0000"));
        spannableString.setSpan(foregroundColorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 改变背景色
    private void showBackgroundColor() {
        SpannableString spannableString = new SpannableString("设置文字的背景色是绿色");
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#FF00FF00"));
        spannableString.setSpan(backgroundColorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 改变字体大小
    private void showRelativeFont() {
        SpannableString spannableString = new SpannableString("万丈高楼平地起");
        RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(1.8f);
        RelativeSizeSpan relativeSizeSpan5 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan relativeSizeSpan6 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan relativeSizeSpan7 = new RelativeSizeSpan(1.2f);

        spannableString.setSpan(relativeSizeSpan1, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan2, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan3, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan4, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan5, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan6, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSizeSpan7, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 显示删除线
    private void showStrikethroughLine() {
        SpannableString spannableString = new SpannableString("为文字设置删除线");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 显示下滑线
    private void showUnderLine() {
        SpannableString spannableString = new SpannableString("为文字设置下滑线");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 设置商标和下标
    private void showSuperAndSub() {

        SpannableString spannableString = new SpannableString("为文字设置上标和下标");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        spannableString.setSpan(subscriptSpan, 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(spannableString);
    }

    // 设置带有风格的文字
    private void showStyleFont() {
        SpannableString spannableString = new SpannableString("为文字设置粗体和斜体");
        StyleSpan styleBold = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleBold, 5, 7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        StyleSpan styleItalic = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleItalic, 8, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(spannableString);
    }

    // 设置图片
    private void showImage() {
        SpannableString spannableString = new SpannableString("这个图片好漂亮");
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        spannableString.setSpan(imageSpan, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }

    // 设置可点击的文字
    private void showClickFont() {
        SpannableString spannableString = new SpannableString("为文字设置点击事件");
        ClickableSpan clickableSpan = new MyClickableSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(clickableSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(Color.parseColor("#36969696"));
        tv.setText(spannableString);
    }

    // 设置url
    private void showUrl() {
        SpannableString spannableString = new SpannableString("为文字设置超链接");
        URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(Color.parseColor("#36969696"));
        tv.setText(spannableString);
    }

    class MyClickableSpan extends ClickableSpan {

        private String content;

        public MyClickableSpan(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            /*Intent intent = new Intent(MainActivity.this, OtherActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("content", content);
            intent.putExtra("bundle", bundle);
            startActivity(intent);*/
            Toast.makeText(MainActivity.this, "点击了文字", Toast.LENGTH_SHORT).show();
        }
    }
}
