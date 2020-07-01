package com.talkweb.lxl.cockroachlib;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author : LongXiaolin
 * @date : 2020/6/8
 * Email   :528953828@qq.com
 * description :
 */
public class CrashErrorUtil {

    private CrashErrorUtil() {
    }

    public static synchronized CrashErrorUtil getInstance() {
        return HttpErrorUtilHolder.INSTANCE;
    }

    private static class HttpErrorUtilHolder {
        private static final CrashErrorUtil INSTANCE = new CrashErrorUtil();
    }

    /**
     * 将异常错误转str
     * @param e
     * @return
     */
    public static String getErrorMessage(Throwable e) {
        String msg = null;
        if (e instanceof UndeclaredThrowableException) {
            Throwable targetEx = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
            if (targetEx != null) {
                msg = targetEx.getMessage();
            }
        } else {
            msg = e.getMessage();
        }
        return msg;
    }
}
