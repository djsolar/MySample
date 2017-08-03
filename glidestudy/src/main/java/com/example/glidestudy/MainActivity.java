package com.example.glidestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        Button load = (Button) findViewById(R.id.button);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLocalImage();
            }
        });
        int size = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
        Toast.makeText(this, "内存占用" + size + "M", Toast.LENGTH_SHORT).show();
    }

    public void loadNetImage() {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this).load(url).error(R.mipmap.ic_launcher).into(iv);
    }

    public void loadAssertImage() {
        Glide.with(this).load("file:///android_asset/b640.jpg").into(iv);
    }

    public void loadResourceImage() {
        Glide.with(this).load(R.mipmap.a640).into(iv);
    }

    public void loadLocalImage() {
        Glide.with(this).load("/mnt/sdcard/c640.jpg").override(100, 100).into(iv);
    }

    public void loadBitmapArrayImage() throws IOException {

        InputStream is = getResources().openRawResource(R.raw.d650);
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        Glide.with(this).load(buffer).placeholder(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }


}
