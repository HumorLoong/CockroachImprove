package com.talkweb.lxl.cockroachlib.util;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.talkweb.lxl.cockroachlib.ExceptionHandler;

import androidx.annotation.NonNull;


/**
 * @author : LongXiaolin
 * @date : 2020/6/22
 * Email   :528953828@qq.com
 * description : 代理Loop机制实现异常处理,实现app不闪退
 */
public final class CockroachImproveUtil {

    private CockroachImproveUtil() {
    }

    private static ExceptionHandler sExceptionHandler;
    private static Thread.UncaughtExceptionHandler sUncaughtExceptionHandler;
    /**
     * 标记位，避免重复安装卸载
     */
    private static boolean sInstalled = false;

    /**
     * 当主线程或子线程抛出异常时会调用exceptionHandler.handlerException(Thread thread, Throwable throwable)
     * <p>
     * exceptionHandler.handlerException可能运行在非UI线程中。
     * <p>
     *
     * @param exceptionHandler
     */
    public static synchronized void install(ExceptionHandler exceptionHandler) {
        if (sInstalled) {
            return;
        }
        sInstalled = true;
        sExceptionHandler = exceptionHandler;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        if (e instanceof QuitCockroachException) {
                            return;
                        }
                        if (sExceptionHandler != null) {
                            //主线程异常
                            e.printStackTrace();
                            String filePath = "";
                            //判断是否是二次进行报错
                            if (!isAgainUselessError(e)) {
                                filePath = CrashHandlerFileUtil.dumpExceptionToSDCard(e);
                            }
                            sExceptionHandler.handlerException(Looper.getMainLooper().getThread(), e, filePath);
                        }
                    }
                }
            }
        });

        sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
                //子线程异常
                throwable.printStackTrace();
                if (sExceptionHandler != null) {
                    String filePath = "";
                    if (!isAgainUselessError(throwable)) {
                        filePath = CrashHandlerFileUtil.dumpExceptionToSDCard(throwable);
                    }
                    sExceptionHandler.handlerException(thread, throwable, filePath);
                }
            }
        });
    }

    /**
     * 卸载异常处理
     */
    public static synchronized void uninstall() {
        if (!sInstalled) {
            return;
        }
        sInstalled = false;
        sExceptionHandler = null;
        //卸载后恢复默认的异常处理逻辑，否则主线程再次抛出异常后将导致ANR，并且无法捕获到异常位置
        Thread.setDefaultUncaughtExceptionHandler(sUncaughtExceptionHandler);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                throw new QuitCockroachException("Quit Cockroach.....");//主线程抛出异常，迫使 while (true) {}结束
            }
        });
    }

    /**
     * 是否因为异常造成的二次报错
     *
     * @param e
     * @return
     */
    private static boolean isAgainUselessError(Throwable e) {
        String errMsg = "Attempt to read from field 'android.app.Activity android.app.ActivityThread$ActivityClientRecord.activity' on a null object reference";
        return TextUtils.equals(e.getMessage(), errMsg);
    }
}