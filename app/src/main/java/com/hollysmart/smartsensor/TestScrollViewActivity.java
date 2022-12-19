package com.hollysmart.smartsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hollysmart.sensordata.Sensorapi;

public class TestScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll_view);
        Sensorapi.getInstance().setModuleId("1577579828644286465");
    }
}