package com.talkweb.lxl.cockroachlib;

import com.talkweb.lxl.cockroachlib.util.CockroachImproveUtil;

/**
 * @author : LongXiaolin
 * @date : 2020/7/1
 * Email   :528953828@qq.com
 * description : app应用不闪退
 */
public class CockroachImprove {

    /**
     * 当主线程或子线程抛出异常时会调用exceptionHandler.handlerException(Thread thread, Throwable throwable)
     * <p>
     * exceptionHandler.handlerException可能运行在非UI线程中。
     * <p>
     *
     * @param exceptionHandler
     */
    public static synchronized void install(ExceptionHandler exceptionHandler) {
        CockroachImproveUtil.install(exceptionHandler);
    }

    /**
     * 卸载异常处理
     */
    public static synchronized void uninstall() {
        CockroachImproveUtil.uninstall();
    }
}
