package com.hollysmart.sensordata.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SensorData {


    @SerializedName("data")
    private List<DataInfo> data;

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    public static class DataInfo {
        @SerializedName("lib_version")
        private String libVersion;
        @SerializedName("platform")
        private String platform;
        @SerializedName("module")
        private String module;
        @SerializedName("session")
        private String session;
        @SerializedName("time")
        private String time;
        @SerializedName("action")
        private String action;
        @SerializedName("action_attach")
        private ActionAttachInfo actionAttach;
        @SerializedName("moduleId")
        private String moduleId;
        @SerializedName("userinfo")
        private UserinfoInfo userinfo;
        @SerializedName("menu")
        private MenuInfo menu;
        @SerializedName("appkey")
        private String appkey;
        @SerializedName("page")
        private String page;
        @SerializedName("page_title")
        private String pageTitle;
        @SerializedName("refer")
        private String refer;
        @SerializedName("duration")
        private String duration;
        @SerializedName("deviceid")
        private String deviceid;
        @SerializedName("deviceinfo")
        private DeviceinfoInfo deviceinfo;
        @SerializedName("language")
        private String language;
        @SerializedName("properties")
        private PropertiesInfo properties;

        public String getLibVersion() {
            return libVersion;
        }

        public void setLibVersion(String libVersion) {
            this.libVersion = libVersion;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public ActionAttachInfo getActionAttach() {
            return actionAttach;
        }

        public void setActionAttach(ActionAttachInfo actionAttach) {
            this.actionAttach = actionAttach;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public UserinfoInfo getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoInfo userinfo) {
            this.userinfo = userinfo;
        }

        public MenuInfo getMenu() {
            return menu;
        }

        public void setMenu(MenuInfo menu) {
            this.menu = menu;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageTitle() {
            return pageTitle;
        }

        public void setPageTitle(String pageTitle) {
            this.pageTitle = pageTitle;
        }

        public String getRefer() {
            return refer;
        }

        public void setRefer(String refer) {
            this.refer = refer;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public DeviceinfoInfo getDeviceinfo() {
            return deviceinfo;
        }

        public void setDeviceinfo(DeviceinfoInfo deviceinfo) {
            this.deviceinfo = deviceinfo;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public PropertiesInfo getProperties() {
            return properties;
        }

        public void setProperties(PropertiesInfo properties) {
            this.properties = properties;
        }

        public static class ActionAttachInfo {
            @SerializedName("time")
            private String time;
            @SerializedName("page")
            private String page;
            @SerializedName("page_title")
            private String pageTitle;
            @SerializedName("refer")
            private String refer;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getPageTitle() {
                return pageTitle;
            }

            public void setPageTitle(String pageTitle) {
                this.pageTitle = pageTitle;
            }

            public String getRefer() {
                return refer;
            }

            public void setRefer(String refer) {
                this.refer = refer;
            }
        }

        public static class UserinfoInfo {

            @SerializedName("user_id")
            private String userId;
            @SerializedName("user_name")
            private String userName;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

        public static class MenuInfo {
        }

        public static class DeviceinfoInfo {
            @SerializedName("os_version")
            private String osVersion;
            @SerializedName("device_type")
            private String deviceType;
            @SerializedName("browser")
            private String browser;
            @SerializedName("browser_version")
            private String browserVersion;
            @SerializedName("browser_engine")
            private String browserEngine;
            @SerializedName("resolution")
            private String resolution;
            @SerializedName("screen_color")
            private Integer screenColor;
            @SerializedName("flash_version")
            private String flashVersion;
            @SerializedName("java_enabled")
            private String javaEnabled;
            @SerializedName("cookie_enabled")
            private String cookieEnabled;

            public String getOsVersion() {
                return osVersion;
            }

            public void setOsVersion(String osVersion) {
                this.osVersion = osVersion;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getBrowser() {
                return browser;
            }

            public void setBrowser(String browser) {
                this.browser = browser;
            }

            public String getBrowserVersion() {
                return browserVersion;
            }

            public void setBrowserVersion(String browserVersion) {
                this.browserVersion = browserVersion;
            }

            public String getBrowserEngine() {
                return browserEngine;
            }

            public void setBrowserEngine(String browserEngine) {
                this.browserEngine = browserEngine;
            }

            public String getResolution() {
                return resolution;
            }

            public void setResolution(String resolution) {
                this.resolution = resolution;
            }

            public Integer getScreenColor() {
                return screenColor;
            }

            public void setScreenColor(Integer screenColor) {
                this.screenColor = screenColor;
            }

            public String getFlashVersion() {
                return flashVersion;
            }

            public void setFlashVersion(String flashVersion) {
                this.flashVersion = flashVersion;
            }

            public String getJavaEnabled() {
                return javaEnabled;
            }

            public void setJavaEnabled(String javaEnabled) {
                this.javaEnabled = javaEnabled;
            }

            public String getCookieEnabled() {
                return cookieEnabled;
            }

            public void setCookieEnabled(String cookieEnabled) {
                this.cookieEnabled = cookieEnabled;
            }
        }

        public static class PropertiesInfo {
            @SerializedName("country")
            private String country;
            @SerializedName("region")
            private String region;
            @SerializedName("city")
            private String city;
            @SerializedName("mccmnc")
            private String mccmnc;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getMccmnc() {
                return mccmnc;
            }

            public void setMccmnc(String mccmnc) {
                this.mccmnc = mccmnc;
            }
        }
    }
}
