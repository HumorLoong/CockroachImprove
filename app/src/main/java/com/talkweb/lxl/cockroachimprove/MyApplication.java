package com.talkweb.lxl.cockroachimprove;

import android.app.Application;

import com.talkweb.lxl.cockroachlib.CockroachImprove;
import com.talkweb.lxl.cockroachlib.CrashErrorUtil;
import com.talkweb.lxl.cockroachlib.EmptyActivity;
import com.talkweb.lxl.cockroachlib.ErrorActivity;
import com.talkweb.lxl.cockroachlib.ExceptionHandler;

/**
 * @author : LongXiaolin
 * @date : 2020/6/8
 * Email   :528953828@qq.com
 * description :
 */
public class MyApplication extends Application implements ExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        CockroachImprove.install(this);
    }

    /**
     * 预留错误回调给app module做bug统计等
     * @param thread
     * @param throwable
     * @param filePath
     */
    @Override
    public void handlerException(Thread thread, Throwable throwable,String filePath) {
        if (SettingManager.getInstance().getIsShowError(this,false)) {
            ErrorActivity.start(this, CrashErrorUtil.getErrorMessage(throwable),filePath);
        }else {
            EmptyActivity.start(this);
        }
    }
}
