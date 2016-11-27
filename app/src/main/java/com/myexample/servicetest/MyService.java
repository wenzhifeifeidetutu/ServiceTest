package com.myexample.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by 19008 on 2016/11/23.
 */

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload: ");

        }

        public int getProgress() {
            Log.d("MyService","getProgress:" );
            return 0;
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "onBind: success");
        return mBinder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate success!");
        //前台服务是应用通知类型只是有些不一样
//        Notification notificationservice = new Notification(R.mipmap.ic_launcher, "通知",System.currentTimeMillis());
        Intent notificationIntent  = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notificationIntent.setLatestEventInfo(this, )
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("service title")
                .setContentText("Servivce contentText")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setTicker("服务").build();
        startForeground(1, notification);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId ) {
        Log.d("MyService", "onStartCommand:  is success");
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public void onDestroy() {
        Log.d("MyService", "onDestroy:  is success");
        super.onDestroy();
    }

}
