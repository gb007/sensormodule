package com.hollysmart.sensordata;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.hollysmart.sensordata.api.PostSensorsDataUtil;
import com.hollysmart.sensordata.base.GlobalData;
import com.hollysmart.sensordata.bean.SensorData;
import com.hollysmart.sensordata.utils.Tools;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class SensorPrivate {

    private final String TAG = "SensorPrivate";
    //页面打开action
    private final String ACTION_OPEN = "$launch";
    //页面浏览action
    private final String ACTION_DELAY = "$delay";
    //页面关闭action
    private final String ACTION_CLOSE = "$close";

    // 记录所有的打开的activity类的列表
    private List list = new ArrayList();

    //忽略不采集的Acivity
    private Set<String> ignoreActivitys;


    //触发滑动监听距离px
    private float MOVE_DSITANCE = 50;

    //上次滑动触发时间
    private Timestamp lastMoveTime;

    /*滑动间隔时间*/
    private final int DUPLICATE_TIME = 1000;

    private final long task_blank_1 = 0;
    private final long task_blank_2 = 2000;
    private final long task_blank_3 = 4000;


    //滑动起始X，Y
    private float posX = 0;
    private float posY = 0;
    //滑动结束X，Y
    private float curPosX = 0;
    private float curPosY = 0;

    private Timer timer = null;
    private TimerTask timerTask_1 = null;
    private TimerTask timerTask_2 = null;
    private TimerTask timerTask_3 = null;

    private Handler handler;

    public SensorPrivate() {

        timer = new Timer();
        ignoreActivitys = new HashSet<>();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);


            }
        };

    }

    /**
     * 打开Activity监测
     *
     * @param activity
     */
    public void trackAppViewScreen(Activity activity) {

        Log.v(TAG, "打开页面" + activity.getClass().getCanonicalName());
        postSensorAction(activity, ACTION_OPEN);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetTimerTask(activity);
            }
        }, 1000);
    }

    /**
     * 关闭Activity监测
     *
     * @param activity
     */
    public void trackAppViewScreenStop(Activity activity) {
        Log.v(TAG, "关闭页面" + activity.getClass().getCanonicalName());
        postSensorAction(activity, ACTION_CLOSE);
        cancelTimerTask();
    }

    /**
     * Activity滑动监测
     *
     * @param activity
     */
    public void trackAppViewScreenMoved(Activity activity) {
        Log.v(TAG, "滑动页面" + activity.getClass().getCanonicalName());
        resetTimerTask(activity);
    }

    /**
     * 注册Activity监听回调
     *
     * @param application
     */
    public void registerActivityLifeCallback(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                lastMoveTime = new Timestamp(System.currentTimeMillis());

                if (null != activity && !ignoreActivitys.contains(activity.getClass().getCanonicalName())) {
                    //滑动事件监听
                    Window window = activity.getWindow();
                    window.setCallback(new MonitorCalback(activity, activity.getWindow().getCallback()));
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (null != activity && !ignoreActivitys.contains(activity.getClass().getCanonicalName())) {
                    //APP页面进入（可见）
                    trackAppViewScreen(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (null != activity && !ignoreActivitys.contains(activity.getClass().getCanonicalName())) {
                    //APP页面退出
                    trackAppViewScreenStop(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });


    }


    /**
     * 判断Activity触摸是否为滑动事件，触发重置计时器操作
     *
     * @param activity
     * @param event
     */
    private void activityMovedJudge(Activity activity, MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                posX = event.getX();

                posY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:

//                curPosX = event.getX();
//
//                curPosY = event.getY();

                break;

            case MotionEvent.ACTION_UP:

                curPosX = event.getX();

                curPosY = event.getY();

                if ((curPosX - posX > 0) && (Math.abs(curPosX - posX) > MOVE_DSITANCE)) {

//                    Log.v(TAG, "向左滑动");

                } else if ((curPosX - posX < 0) && (Math.abs(curPosX - posX) > MOVE_DSITANCE)) {

//                    Log.v(TAG, "向右滑动");

                }

                if ((curPosY - posY > 0) && (Math.abs(curPosY - posY) > MOVE_DSITANCE)) {

//                    Log.v(TAG, "向下滑动");

                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                    if (currentTime.getTime() - lastMoveTime.getTime() > DUPLICATE_TIME) {
                        trackAppViewScreenMoved(activity);
                        lastMoveTime = new Timestamp(System.currentTimeMillis());
                    }


                } else if ((curPosY - posY < 0) && (Math.abs(curPosY - posY) > MOVE_DSITANCE)) {

//                    Log.v(TAG, "向上滑动");

                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                    if (currentTime.getTime() - lastMoveTime.getTime() > DUPLICATE_TIME) {
                        trackAppViewScreenMoved(activity);
                        lastMoveTime = new Timestamp(System.currentTimeMillis());
                    }

                }

                break;

        }
    }

    /**
     * 重置timerTask任务计时
     */
    private void resetTimerTask(Activity activity) {

        if (null != timerTask_1)
            timerTask_1.cancel();

        if (null != timerTask_2)
            timerTask_2.cancel();

        if (null != timerTask_3)
            timerTask_3.cancel();

        timerTask_1 = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                Log.v(TAG, "task1执行");
                postSensorAction(activity, ACTION_DELAY);
                timer.schedule(timerTask_2, task_blank_2);
            }
        };

        timerTask_2 = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                Log.v(TAG, "task2执行");
                postSensorAction(activity, ACTION_DELAY);
                timer.schedule(timerTask_3, task_blank_3);
            }
        };
        timerTask_3 = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                Log.v(TAG, "task3执行");
                postSensorAction(activity, ACTION_DELAY);
            }
        };
        timer.schedule(timerTask_1, task_blank_1);
    }

    /**
     * 取消所有timeTask任务
     */
    private void cancelTimerTask() {

        if (null != timerTask_1)
            timerTask_1.cancel();

        if (null != timerTask_2)
            timerTask_2.cancel();

        if (null != timerTask_3)
            timerTask_3.cancel();
    }

    /**
     * 发送用户行为到服务器
     * @param activity
     * @param action 行为 "$launch"打开页面  "$close"关闭页面   "$delay"停留浏览
     */
    private void postSensorAction(Activity activity, String action) {


        SensorData.DataInfo dataInfo = new SensorData.DataInfo();
        dataInfo.setLibVersion("0.0.1");
        dataInfo.setPlatform("PC");
        dataInfo.setModule("WEB");
        dataInfo.setSession("");
        dataInfo.setTime(Tools.yyyy_MM_DD_HH_mm_format(System.currentTimeMillis()));
        dataInfo.setAction(action);

        //moduleId
        dataInfo.setModuleId(GlobalData.moduleId);

        dataInfo.setAppkey(GlobalData.appKey);
        dataInfo.setPage(activity.getClass().getCanonicalName());
        dataInfo.setPageTitle(activity.getTitle().toString());
        dataInfo.setRefer(GlobalData._SERVER_ADDRESS);
        dataInfo.setDuration("0");
        //TODO
        dataInfo.setDeviceid("");

        dataInfo.setLanguage("zh-CN");


        //ActionAttachInfo
        SensorData.DataInfo.ActionAttachInfo actionAttachInfo = new SensorData.DataInfo.ActionAttachInfo();
        actionAttachInfo.setTime("1000");
        actionAttachInfo.setPage(activity.getClass().getCanonicalName());
        actionAttachInfo.setPageTitle(activity.getTitle().toString());
        actionAttachInfo.setRefer(GlobalData._SERVER_ADDRESS);
        dataInfo.setActionAttach(actionAttachInfo);


        //UserinfoInfo
        SensorData.DataInfo.UserinfoInfo userinfoInfo = new SensorData.DataInfo.UserinfoInfo();
        userinfoInfo.setUserId(GlobalData.userId);
        userinfoInfo.setUserName(GlobalData.userName);
        dataInfo.setUserinfo(userinfoInfo);


        //MenuInfo
        SensorData.DataInfo.MenuInfo menuInfo = new SensorData.DataInfo.MenuInfo();
        dataInfo.setMenu(menuInfo);


        //DeviceinfoInfo
        SensorData.DataInfo.DeviceinfoInfo deviceinfoInfo = new SensorData.DataInfo.DeviceinfoInfo();
        deviceinfoInfo.setOsVersion("");
        deviceinfoInfo.setDeviceType("PC");
        deviceinfoInfo.setBrowser("");
        deviceinfoInfo.setBrowserVersion("");
        deviceinfoInfo.setBrowserEngine("");
        deviceinfoInfo.setResolution("");
        deviceinfoInfo.setScreenColor(24);
        deviceinfoInfo.setFlashVersion("");
        deviceinfoInfo.setJavaEnabled("false");
        deviceinfoInfo.setCookieEnabled("true");
        dataInfo.setDeviceinfo(deviceinfoInfo);


        //PropertiesInfo
        SensorData.DataInfo.PropertiesInfo propertiesInfo = new SensorData.DataInfo.PropertiesInfo();
        propertiesInfo.setCity("");
        propertiesInfo.setCountry("");
        propertiesInfo.setRegion("");
        propertiesInfo.setMccmnc("");
        dataInfo.setProperties(propertiesInfo);

        //List<SensorData.DataInfo>
        List<SensorData.DataInfo> dataInfos = new ArrayList<>();
        dataInfos.add(dataInfo);


        //SensorData
        SensorData sensorData = new SensorData();
        sensorData.setData(dataInfos);

//        String testData = new Gson().toJson(sensorData);
        PostSensorsDataUtil.postSensorData(new Gson().toJson(sensorData));
    }


    class MonitorCalback implements Window.Callback {

        Window.Callback localCallback;
        Activity activity;

        public MonitorCalback(Activity activity, Window.Callback callback) {
            this.localCallback = callback;
            this.activity = activity;
        }


        @Override

        public boolean dispatchKeyEvent(KeyEvent event) {

            return localCallback.dispatchKeyEvent(event);

        }


        @Override

        public boolean dispatchKeyShortcutEvent(KeyEvent event) {

            return localCallback.dispatchKeyShortcutEvent(event);

        }

        @Override

        public boolean dispatchTouchEvent(MotionEvent event) {
            activityMovedJudge(activity, event);
            return localCallback.dispatchTouchEvent(event);

        }

        @Override

        public boolean dispatchTrackballEvent(MotionEvent event) {

            return localCallback.dispatchTrackballEvent(event);

        }


        @Override

        public boolean dispatchGenericMotionEvent(MotionEvent event) {

            return localCallback.dispatchGenericMotionEvent(event);

        }

        @Override

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {

            return localCallback.dispatchPopulateAccessibilityEvent(event);

        }

        @Override

        public View onCreatePanelView(int featureId) {

            return localCallback.onCreatePanelView(featureId);

        }

        @Override

        public boolean onCreatePanelMenu(int featureId, Menu menu) {

            return localCallback.onCreatePanelMenu(featureId, menu);

        }

        @Override

        public boolean onPreparePanel(int featureId, View view, Menu menu) {

            boolean ret = localCallback.onPreparePanel(featureId, view, menu);

            return ret;

        }

        @Override

        public boolean onMenuOpened(int featureId, Menu menu) {

            return localCallback.onMenuOpened(featureId, menu);

        }

        @Override

        public boolean onMenuItemSelected(int featureId, MenuItem item) {

            return localCallback.onMenuItemSelected(featureId, item);

        }

        @Override

        public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {

            localCallback.onWindowAttributesChanged(attrs);

        }

        @Override

        public void onContentChanged() {

            localCallback.onContentChanged();

        }

        @Override

        public void onWindowFocusChanged(boolean hasFocus) {

            Log.d("", "ttest onfocus changed called");

            localCallback.onWindowFocusChanged(hasFocus);

        }

        @Override

        public void onAttachedToWindow() {

            localCallback.onAttachedToWindow();

        }

        @Override

        public void onDetachedFromWindow() {

            localCallback.onDetachedFromWindow();

        }

        @Override

        public void onPanelClosed(int featureId, Menu menu) {

            localCallback.onPanelClosed(featureId, menu);

        }

        @Override

        public boolean onSearchRequested() {

            return localCallback.onSearchRequested();

        }

        @Override
        public boolean onSearchRequested(SearchEvent searchEvent) {
            return false;
        }


        @Override

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {

            return localCallback.onWindowStartingActionMode(callback);

        }

        @Nullable
        @Override
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return null;
        }


        @Override

        public void onActionModeStarted(ActionMode mode) {

            localCallback.onActionModeStarted(mode);

        }


        @Override

        public void onActionModeFinished(ActionMode mode) {

            localCallback.onActionModeFinished(mode);

        }

        @Override
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
    }


    /**
     * 添加忽略的Activity
     * @param ignoreAcitivityList 忽略的Activity完整的包名路径List集合
     */
    public void addIgnoreActivitys(List ignoreAcitivityList) {
        if(ignoreAcitivityList != null)
        ignoreActivitys.addAll(ignoreAcitivityList);
    }

}
