package com.hollysmart.sensordata.api;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.hollysmart.sensordata.bean.SensorData;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.HashMap;

import rx.Subscriber;

public class PostSensorsDataUtil {


    public static void postSensorData(String data) {

        Subscriber<HashMap<String, Object>> ocrSubscriber = new Subscriber<HashMap<String, Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                if (e instanceof SocketTimeoutException) {
//                    Log.e("PostSensorsDataUtil", getString(R.string.network_connection_timeout));
                    Log.e("PostSensorsDataUtil", "行为采集数据上传失败！" + e.getMessage());

                } else {

//                    Log.e("PostSensorsDataUtil", getString(R.string.network_connection_timeout));
                    Log.e("PostSensorsDataUtil", "行为采集数据上传失败！" + e.getMessage());
                }

            }

            @Override
            public void onNext(HashMap<String, Object> result) {

                try {
                    JSONObject object = new JSONObject(result);
                    if (null != object && object.has("flag")) {
                        String flag = object.getString("flag");
                        if (flag.equals("1")) {
                            Log.e("PostSensorsDataUtil", "行为采集数据上传成功！");
                        } else {
                            Log.e("PostSensorsDataUtil", "行为采集数据上传失败！");
                        }
                    } else {
                        Log.e("PostSensorsDataUtil", "行为采集数据上传失败！");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("PostSensorsDataUtil", "行为采集数据上传失败！");
                }

            }
        };

        APIManager.getInstance().postSensorData(data, ocrSubscriber);

    }

}
