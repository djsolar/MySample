package com.example.userinterationtest;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.onLoginInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PopupWindowActivity.class);
                startActivity(intent);
            }
        });
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });


        Button btn3 = (Button) findViewById(R.id.button3);
       /* btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleDialog();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMultiDialog();
            }
        });*/

       /* Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getResources().getBoolean(R.bool.is_large)) {
                    CustomDialogFragment fragment = new CustomDialogFragment();
                    fragment.show(getSupportFragmentManager(), "custom dialog");
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new CustomDialogFragment()).commit();
                }
            }
        });
*/


        // Toast.makeText(this, "使用第三方库显示", Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "使用自定义Toast", Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "使用自定义Toast1", Toast.LENGTH_SHORT).show();
       //  CustomToast.showToast(this, "显示第一次", Toast.LENGTH_LONG);
        // CustomToast.showToast(this, "显示第二次", Toast.LENGTH_LONG);
    }

    private void showSingleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(new String[] {"选项1", "选项2", "选项3", "选项4", "选项5"}, 1, null);
        builder.create().show();
    }

    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setIcon(R.mipmap.ic_launcher);
        builder.setItems(new String[] {"选项1", "选项2", "选项3", "选项4", "选项5"}, null);
        builder.create().show();
    }

    private void showMultiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(new String[] {"选项1", "选项2", "选项3", "选项4", "选项5"}, new boolean[]{false, false, false, false, false}, null);
        builder.create().show();
    }

    @Override
    public void onLoginInputComplete(String account, String password) {
        Toast.makeText(this, "账号：" + account + ", 密码：" + password, Toast.LENGTH_SHORT).show();
    }
}
