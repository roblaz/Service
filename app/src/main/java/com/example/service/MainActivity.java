package com.example.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    boolean bound = false;

    Intent intent;
    MyService service;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tv_text);

        intent = new Intent(this, MyService.class);
        tvText.setText("Disconnected");

    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (service == null) service = ((MyService.MyBinder) iBinder).getService();
        tvText.setText("Connected");


    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        service = null;
        

    }

    public void onClickOnConnected(View v) {
        bindService(intent, this, Context.BIND_AUTO_CREATE);

    }



    public void onServiceData(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction("data");
        startService(intent);

        tvText.setText(service.getData());

    }
    public void onServiceTime(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction("time");
        startService(intent);
        tvText.setText(service.getTime());
    }
    @Override
    public void onDestroy(){
        if (intent != null) stopService(intent);
        super.onDestroy();
    }
}

