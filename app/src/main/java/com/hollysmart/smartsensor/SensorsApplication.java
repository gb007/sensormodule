package com.hollysmart.smartsensor;

import android.app.Application;
import android.content.Context;

import com.hollysmart.sensordata.Sensorapi;

public class SensorsApplication extends Application {

    private static SensorsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSensors();
    }


    public static SensorsApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }


    private void initSensors() {
        String serverHostUrl = "http://192.168.32.96";   //服务器地址
        String appKey = "9224a3a474534cc2b5ca0fdcf4531c04";        //app密钥
        String userId = "test123";        //用户Id
        Sensorapi.init(serverHostUrl,appKey,userId,this);
    }


}
