package com.example.drawabletest

import android.graphics.drawable.RotateDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class CdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cd)

        val iv = findViewById(R.id.cd) as ImageView
        val iv1 = findViewById(R.id.handler) as ImageView
        val backIv = iv.drawable as RotateDrawable
        val backIv1 = iv1.drawable as RotateDrawable
        var hander:Handler = Handler()
        hander.postDelayed({
            backIv.level = 10000
            backIv1.level = 10000
        }, 3000)
    }
}
