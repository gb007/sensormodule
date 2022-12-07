package com.hollysmart.sensordata;

import android.app.Application;
import android.util.Log;

import com.hollysmart.sensordata.base.GlobalData;

import org.json.JSONObject;

import java.util.List;

public class Sensorapi {
    private static final Object key_lock = new Object();
    private static Sensorapi INSTANCE;
    private static String serverHostUrl = "";   //服务器地址
    private static String appKey = "";        //app密钥
    private static String userId = "";        //用户Id

    private Sensorapi(String serverHostUrl, String appKey, String userId, List ignoreAcititys, Application application) {
        this.serverHostUrl = serverHostUrl;
        this.appKey = appKey;
        this.userId = userId;
        GlobalData._SERVER_ADDRESS = serverHostUrl;
        GlobalData.appKey = appKey;
        GlobalData.userId = userId;
        SensorPrivate  sensorPrivate= new SensorPrivate();
        sensorPrivate.addIgnoreActivitys(ignoreAcititys);
        sensorPrivate.registerActivityLifeCallback(application);
    }

    public static Sensorapi init(String serverHostUrl, String appKey, String userId, List ignoreAcititys,Application application) {
        synchronized (key_lock) {
            if (null == INSTANCE) {
                INSTANCE = new Sensorapi(serverHostUrl, appKey, userId, ignoreAcititys,application);
            }
        }
        return INSTANCE;
    }

    // 获取对象
    public static Sensorapi getInstance() {
        return INSTANCE;
    }

    // 最终监控的AppViewScreen(所有的activity对象)并且打印出他们的信息
    public void track(String $AppViewScreen, JSONObject propertise) {
        Log.e("zero", propertise.toString());
    }
}
