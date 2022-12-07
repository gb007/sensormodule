package com.hollysmart.sensordata;

import android.app.Application;
import android.util.Log;

import com.hollysmart.sensordata.base.GlobalData;

import org.json.JSONObject;

import java.util.List;

public class Sensorapi {
    private static final Object key_lock = new Object();
    private static Sensorapi INSTANCE;

    /**
     * 初始化用户行为采集
     * @param serverHostUrl     上传行为数据的服务器地址
     * @param appKey            app密钥
     * @param userId            用户Id
     * @param ignoreAcititys    忽略采集的Activity集合
     * @param application
     * @return
     */
    public void setSensorapiData(String serverHostUrl, String appKey, String userId, List ignoreAcititys, Application application) {
        GlobalData._SERVER_ADDRESS = serverHostUrl;
        GlobalData.appKey = appKey;
        GlobalData.userId = userId;
        SensorPrivate  sensorPrivate= new SensorPrivate();
        sensorPrivate.addIgnoreActivitys(ignoreAcititys);
        sensorPrivate.registerActivityLifeCallback(application);
    }


    public static Sensorapi getInstance() {
        synchronized (key_lock) {
            if (null == INSTANCE) {
                INSTANCE = new Sensorapi();
            }
        }
        return INSTANCE;
    }

}
