package com.hollysmart.sensordata.utils;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class LogHelper {
    // 是否打印log
    private static boolean isLog = true;

    private static String logTag = "zhylTag";

    public static void i(String tag, String msg) {
        if (isLog) {
            if (tag == null) {
                tag = logTag;
            }
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLog) {
            if (tag == null) {
                tag = logTag;
            }
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            if (tag == null) {
                tag = logTag;
            }
            Log.v(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLog) {
            if (tag == null) {
                tag = logTag;
            }
            Log.e(tag, msg);
        }
    }

    public static void writeLog2File(String log,String dirName){
        //String dirName = SdcardUtils.getAppScanLogPath();
        File dir = new File(dirName);
        if (!dir.exists())
            dir.mkdirs();
        CharSequence time = Tools.yyyy_MM_DD_HH_mm_format_forcrash(System.currentTimeMillis());
        String fileName = dirName +  time + ".txt";
        File crashFile = new File(fileName);
        if(!crashFile.exists()){
            try {
                crashFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        do {
            PrintWriter ps = null;
            try {
                ps = new PrintWriter(crashFile);
            } catch (FileNotFoundException e) {
                break;
            }
            String msg = "http request at " + time + "\r\n" + log;
            ps.write(msg);
            ps.flush();
            ps.close();
        } while (false);
    }

}
