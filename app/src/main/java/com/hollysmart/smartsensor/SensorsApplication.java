package com.hollysmart.smartsensor;

import android.app.Application;
import android.content.Context;

import com.hollysmart.sensordata.Sensorapi;

import java.util.ArrayList;
import java.util.List;

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

        //服务器地址
        String serverHostUrl = "http://192.168.32.95";
        //app密钥
        String appKey = "9224a3a474534cc2b5ca0fdcf4531c04";
        //用户Id
        String userId = "1588084160369565694";
        //
        String userName = "李鸿忠";
        //忽略采集的Activity列表
        List list = new ArrayList();
        //Activity 完整的包命路径
//        list.add("com.hollysmart.smartsensor.MainActivity");
//        list.add("com.hollysmart.smartsensor.TestRecycleViewActivity");
        Sensorapi.getInstance().setSensorapiData(serverHostUrl,appKey,userId,userName,list,this);
    }


}
