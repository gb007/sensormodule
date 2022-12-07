# SensorModule模块使用

## 1.1在工程的根目录build.gradle中添加jitpack库依赖

````

allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
	
````

## 2.在需要引用此类库模块的build.gradle中引入依赖

 ````
dependencies {
	implementation 'com.github.gb007:sensormodule:Tag'
	}

````

## 3.初始化配置信息


### 3.1 Manifest中添加网络相关权限

````
    <!--用于获取运营商信息，用于支持提供运营商信息相关的接口-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
    <!--用于访问wifi网络信息，wifi信息会用于进行网络定位-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
    <!--用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
    <!--用于访问网络，网络定位需要上网-->
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>

````


### 3.2 Application中或者其他引用处初始化SensorModule配置

````
    private void initSensorModule(){
    
        //服务器地址
        String serverHostUrl = "http://192.168.32.96";
        //app密钥
        String appKey = "9224a3a474534cc2b5ca0fdcf4531c04";
        //用户Id
        String userId = "test123";
        //忽略采集的Activity列表
        List list = new ArrayList();
        //Activity 完整的包命路径
        list.add("com.hollysmart.smartsensor.MainActivity");
        list.add("com.hollysmart.smartsensor.TestRecycleViewActivity");
        Sensorapi.getInstance().setSensorapiData(serverHostUrl,appKey,userId,list,this);
    }

````
