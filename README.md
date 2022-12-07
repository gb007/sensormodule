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

### 3.1 Application中初始化SensorModule配置

````
    private fun initSensorModule(){
    
        //服务器地址
        String serverHostUrl = "http://192.168.32.96";  
        //app密钥
        String appKey = "9224a3a474534cc2b5ca0fdcf4531c04";  
        //用户Id    
        String userId = "test123";  
             
        Sensorapi.init(serverHostUrl,appKey,userId,this);
    }

````