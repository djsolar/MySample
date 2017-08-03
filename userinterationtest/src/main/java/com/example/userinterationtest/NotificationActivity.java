package com.example.userinterationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotificationActivity extends AppCompatActivity {

    public final static int NOTIFICATION_ID = "NotificationActivity".hashCode();

    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        show = (Button) findViewById(R.id.button7);
        IntentFilter intentFilter = new IntentFilter(BUTTON_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shwoNotify();
            }
        });
    }

    private boolean isPlay = true;

    private static final String BUTTON_ACTION = "android.music.button.action";

    private static final String INTENT_BUTTON_TAG = "buttonId";

    private void showRemoteViews() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_custom_button);
        remoteViews.setImageViewResource(R.id.custom_song_icon, R.drawable.song_name);
        remoteViews.setImageViewResource(R.id.btn_custom_next, android.R.drawable.ic_media_next);
        remoteViews.setTextViewText(R.id.tv_custom_song_name, "七里香");
        remoteViews.setTextViewText(R.id.tv_custom_song_singer, "周杰伦");
        if (isPlay) {
            remoteViews.setImageViewResource(R.id.btn_custom_play, android.R.drawable.ic_media_pause);
        } else {
            remoteViews.setImageViewResource(R.id.btn_custom_play, android.R.drawable.ic_media_play);
        }

        Intent buttonIntent = new Intent(BUTTON_ACTION);
        buttonIntent.putExtra(INTENT_BUTTON_TAG, R.id.btn_custom_play);
        PendingIntent playIntent = PendingIntent.getBroadcast(this, 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_play, playIntent);

        buttonIntent.putExtra(INTENT_BUTTON_TAG, R.id.btn_custom_next);
        PendingIntent nextIntent = PendingIntent.getBroadcast(this, 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_next, nextIntent);

        builder.setContent(remoteViews)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("正在播放")
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        int smallIcon = R.mipmap.ic_launcher;
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        String info = "逢赌必输：我是通知内容";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));

        builder.setSmallIcon(smallIcon) // 设置小图
                .setLargeIcon(bitmap)       // 设置大图
                .setContentTitle("逢赌必输") // 显示标题
                .setContentText(info) // 在标题下面显示内容
                .setContentInfo(info) // 在标题后面显示内容信息
                .setShowWhen(false)
                .setWhen(System.currentTimeMillis()) // 添加通知时间，一盘是系统时间
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS) // 向通知添加声音、灯光、震动等效果
                .setVibrate(new long[]{0, 300, 500, 700}) // 延迟0ms震动300ms延迟500ms震动700ms
                .setLights(0xFF0000FF, 1000, 1000) // 设置三色灯的颜色
                .setSound(Uri.parse("file:///sdcard/sound.mp3")) // 设置声音
                .setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "5")) // 获取多媒体库中的声音
                .setPriority(Notification.PRIORITY_DEFAULT)
                // .setUsesChronometer(true) 计时器
                .setProgress(100, 10, true)
                .setTicker(info)
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
                .setAutoCancel(false)
                .setOngoing(true)
                .setColor(Color.GREEN);
                /*FLAG_ONE_SHOT   表示返回的PendingIntent仅能执行一次，执行完后自动取消
                FLAG_NO_CREATE     表示如果描述的PendingIntent不存在，并不创建相应的PendingIntent，而是返回NULL
                FLAG_CANCEL_CURRENT      表示相应的PendingIntent已经存在，则取消前者，然后创建新的PendingIntent，这个有利于数据保持为最新的，可以用于即时通信的通信场景
                FLAG_UPDATE_CURRENT     表示更新的PendingIntent*/
        Notification notification = builder.build();
        /* 提醒标志符成员：
            Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
            Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
            Notification.FLAG_INSISTENT   //让声音、振动无限循环，直到用户响应 （取消或者打开）
            Notification.FLAG_ONLY_ALERT_ONCE  //发起Notification后，铃声和震动均只执行一次
            Notification.FLAG_AUTO_CANCEL      //用户单击通知后自动消失
            Notification.FLAG_NO_CLEAR          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
            Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务*/
        notification.flags = Notification.FLAG_AUTO_CANCEL; // 设置通知栏的Flags
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private String playStatus = "正在播放";

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BUTTON_ACTION)) {
                int buttonId = intent.getIntExtra(INTENT_BUTTON_TAG, 0);
                switch (buttonId) {
                    case R.id.btn_custom_play:
                        isPlay = !isPlay;
                        if (isPlay) {
                            playStatus = "正在播放";
                        } else {
                            playStatus = "已暂停";
                        }
                        Toast.makeText(context, playStatus, Toast.LENGTH_SHORT).show();
                        showRemoteViews();
                        break;

                    case R.id.btn_custom_next:
                        Toast.makeText(context, "下一曲", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };


    private NotificationCompat.Builder mBuilder;
    public void shwoNotify(){
        //先设定RemoteViews
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.view_custom);
        //设置对应IMAGEVIEW的ID的资源图片
        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.icon);
//		view_custom.setInt(R.id.custom_icon,"setBackgroundResource",R.drawable.icon);
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条");
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
//		view_custom.setTextViewText(R.id.tv_custom_time, String.valueOf(System.currentTimeMillis()));
        //设置显示
//		view_custom.setViewVisibility(R.id.tv_custom_time, View.VISIBLE);
//		view_custom.setLong(R.id.tv_custom_time,"setTime", System.currentTimeMillis());//不知道为啥会报错，过会看看日志
        //设置number
//		NumberFormat num = NumberFormat.getIntegerInstance();
//		view_custom.setTextViewText(R.id.tv_custom_num, num.format(this.number));
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContent(view_custom)
                // .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("有新资讯")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                .setSmallIcon(R.drawable.icon);
//		mNotificationManager.notify(notifyId, mBuilder.build());
        Notification notify = mBuilder.build();
        notify.contentView = view_custom;
//		Notification notify = new Notification();
//		notify.icon = R.drawable.icon;
//		notify.contentView = view_custom;
//		notify.contentIntent = getDefalutIntent(Notification.FLAG_AUTO_CANCEL);
        mNotificationManager.notify(NOTIFICATION_ID, notify);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
