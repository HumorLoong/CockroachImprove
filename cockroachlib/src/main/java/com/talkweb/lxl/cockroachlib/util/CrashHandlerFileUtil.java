package com.talkweb.lxl.cockroachlib.util;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * @author : LongXiaolin
 * @date : 2020/6/24
 * Email   :528953828@qq.com
 * description :
 */
public class CrashHandlerFileUtil {
    /**
     * 定义文件存放路径
     */
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/DsCrashInfo/";

    /**
     * 定义文件后缀
     */
    private static final String FILE_NAME_SUFFIX = ".txt";


    /**
     * 记录异常信息到本地文本中
     *
     * @param throwable
     * @return 保存的文档地址
     */
    public static String dumpExceptionToSDCard(Throwable throwable) {
        //如果SD卡非正常挂载，则用Log输出异常信息
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e(TAG, "SD卡出错");
            return "";
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
            Log.i("CrashHandlerFileUtil", "dumpExceptionToSDCard: "+mkdirs);
        }
        long currentTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(new Date(currentTime));
        //建立记录Crash信息的文本
        File file = new File(PATH + "fbiWarn-" + time + FILE_NAME_SUFFIX);
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            printWriter.print("CrashFileName:"+file.getName()+"\n");
            printWriter.print("CrashTime:"+time+"\n");
            dumpPhoneInfo(printWriter);
            printWriter.println();
            throwable.printStackTrace(printWriter);
            printWriter.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "记录Crash信息失败");
            return "";
        }
    }

    /**
     * 记录手机信息
     *
     * @param printWriter
     */
    private static void dumpPhoneInfo(PrintWriter printWriter) {
        //系统版本号
        printWriter.print("OS Version:");
        printWriter.print(Build.VERSION.RELEASE);
        printWriter.print("_");
        printWriter.print(Build.VERSION.SDK_INT+"\n");
        //硬件制造商
        printWriter.print("Vendor:");
        printWriter.print(Build.MANUFACTURER+"\n");
        //手机型号
        printWriter.print("Brand:");
        printWriter.print(Build.BRAND+"  "+Build.MODEL+"\n");
    }
}
