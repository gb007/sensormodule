package com.hollysmart.sensordata.api;


import com.hollysmart.sensordata.base.GlobalData;
import com.hollysmart.sensordata.bean.SensorData;
import com.hollysmart.sensordata.utils.LogHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class APIManager {

    private OkHttpClient.Builder mBuilder;
    private Retrofit mRetrofit;
    private static String BASE_URL = GlobalData._SERVER_ADDRESS;


    private APIManager() {
        init();
    }

    private void init() {
        mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(GlobalData.TIME_OUT, TimeUnit.SECONDS);
        changeBaseUrl(GlobalData._SERVER_ADDRESS);
    }

    /**
     * 设置头
     */
    public class OkHeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
//            String token = "";
//            token = SharedPreferencedUtils.getString(App.getContext(), "token");
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
//            builder.addHeader(GlobalData.TOKEN_KEY, token);//"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjMyNDMzMjEsInVzZXJuYW1lIjoiMTMyNjkwNjk4MzQifQ.L_7ubhwwnJu_1AwZ9fETApuypHAUspIsmPZ63mhuUHc"
            request = builder.build();
            return chain.proceed(request);
        }

    }


    private void changeBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
        mBuilder.addInterceptor(new OkHeaderInterceptor()); //请求头
        mRetrofit = new Retrofit.Builder()
                .client(mBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private void changeUpdateBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;

        LogHelper.d("ApiManager--baseUrl", baseUrl);

        mRetrofit = new Retrofit.Builder()
                .client(mBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static class PLACEHOLDER {
        private static APIManager sAPIManager = new APIManager();
    }

    public static APIManager getInstance() {
        return PLACEHOLDER.sAPIManager;
    }


    /**
     * 坐标上传
     *
     * @param
     * @param subscriber 观察者
     */
    public void postSensorData(String data, Subscriber subscriber) {
//        String loc_url = GlobalData._SERVER_ADDRESS;
//        changeBaseUrl(loc_url);
        ISensorDataApi iSensorDataApi = mRetrofit.create(ISensorDataApi.class);
        iSensorDataApi.postSensorData(data)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
