package com.hollysmart.sensordata.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    /**
     * 进行URL编码
     *
     * @param src     src
     * @param charset 字符集
     * @return 编码的字符串
     */
    public static String urlEncode(String src, String charset) {
        if (TextUtils.isEmpty(charset)) {
            charset = "utf-8";
        }
        String str = "";
        try {
            str = URLEncoder.encode(src, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 手机号验证
     */
    public static boolean isMobileRight(String mobile) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        boolean isMatch = m.matches();
        return isMatch;
    }

    /**
     * 手机号验证
     */
    public static boolean isNum(String mobile) {
        String reg = "^\\d+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(mobile);
        return m.find();
    }

    /**
     * 座机号码验证
     */
    public static boolean isPhone(String str) {
        boolean isPhone = false;
        if (str.length() != 8){
            return false;
        } else {
            if (str.equals("00000000") || str.equals("11111111") || str.equals("22222222") || str.equals("33333333") || str.equals("44444444") ||
                    str.equals("55555555") || str.equals("66666666") || str.equals("77777777") || str.equals("88888888") || str.equals("99999999")){
                isPhone = false;
            } else {
                isPhone = true;
            }
        }
        return isPhone;
    }


    /**
     * 分隔字符串
     *
     * @param content    字符串
     * @param charCount  隔几个字符
     * @param replaceStr 以什么字符分隔
     * @return 分隔后的字符串
     */
    public static String insertSpaceToString(String content, int charCount, String replaceStr) {
        if (content.length() < charCount) {
            return content;
        }
        int count = 0;
        if (content.length() % charCount == 0) {
            count = content.length() / charCount;
        } else {
            count = content.length() / charCount + 1;
        }
        String result = "";
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                result += content.substring(i * charCount, content.length());
            } else {
                result += content.substring(i * charCount, (i + 1) * charCount) + replaceStr;
            }
        }
        return result;
    }

    public static String getJWD(double lat) {
        double at = lat * 3600;
        int du = (int) (at / 3600);
        int fen = (int) ((at - du * 3600) / 60);
        int miao = (int) ((at - du * 3600) % 60);
        //return du + "°" + fen + "′" + miao + "″";
        return du + "°" + fen + "′";
    }

    public static String yyyy_MM_DD_HH_mm_format_forcrash(long times) {
        String formatString = "yyyy-MM-dd_HH-mm-ss_SSS";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(new Date(times));
    }

    public static String yyyy_MM_DD_HH_mm_format(long times) {
        String formatString = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(new Date(times));
    }


    /**
     * 只能输入中文的判断
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /*
     * 获取当前程序的版本号
     */
    public static String getVersionName(AppCompatActivity activity) {
        try {
            PackageManager packageManager = activity.getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     * 安装apk
//     *
//     * @param file
//     */
//    protected void installApk(Context context, File file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //判断版本是否在7.0以上
//            Uri apkUri = FileProvider.getUriForFile(context, context.getResources().getString(R.string.provider_name), file);//在AndroidManifest中的android:authorities值
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
//            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");//编者按：此处Android应为android，否则造成安装不了
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        context.startActivity(intent);
//    }



    public static void showDialog(Context context, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }


}
