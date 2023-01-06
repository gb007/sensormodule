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
     * @param userName          用户姓名
     * @param ignoreAcititys    忽略采集的Activity集合
     * @param application
     * @return
     */
    public void setSensorapiData(String serverHostUrl, String appKey, String userId,String userName, List ignoreAcititys, Application application) {
        GlobalData._SERVER_ADDRESS = serverHostUrl;
        GlobalData.appKey = appKey;
        GlobalData.userId = userId;
        GlobalData.userName = userName;
        SensorPrivate  sensorPrivate= new SensorPrivate();
        sensorPrivate.addIgnoreActivitys(ignoreAcititys);
        sensorPrivate.registerActivityLifeCallback(application);
    }

    /**
     * 埋点页面的menuId
     */
    public void setModuleId(String moduleId){
        GlobalData.moduleId = moduleId;
    }


    /**
     * 设置用户信息
     */
    public void setUserInfo(String userId,String userName){
        GlobalData.userId = userId;
        GlobalData.userName = userName;
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
