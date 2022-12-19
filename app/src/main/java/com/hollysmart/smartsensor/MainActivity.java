package com.hollysmart.smartsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hollysmart.sensordata.Sensorapi;

public class MainActivity extends AppCompatActivity {

    TextView tv_ry ,tv_sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sensorapi.getInstance().setModuleId("1572184530");
        iniView();
    }

    private void iniView() {

        tv_ry = findViewById(R.id.tv_ry);
        tv_sv = findViewById(R.id.tv_sv);

        tv_ry.setOnClickListener(view -> {
            startActivity( new Intent(MainActivity.this,TestRecycleViewActivity.class));
        });


        tv_sv.setOnClickListener(view -> {
            startActivity( new Intent(MainActivity.this,TestScrollViewActivity.class));
        });

    }


}