package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {



    private String data = "";
    private String time = "";
    private final IBinder binder = new MyBinder();
    private boolean running = false;

    public MyService() {
    }


    public String getTime() {
        return time;
    }

    public String getData() {
        return data;
    }


    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent.getAction().equals("time")) {
            timeFormat();

        } else {
            dataFormat();

        }
        stopSelf();
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        running = false;
        return true;
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public String timeFormat(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        time = sdfDate.format(now);
        return time;

    }
    public String dataFormat(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        data = sdfDate.format(now);
        return data;
    }

}
