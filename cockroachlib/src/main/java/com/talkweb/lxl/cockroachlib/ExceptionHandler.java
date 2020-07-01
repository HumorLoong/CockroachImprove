package com.talkweb.lxl.cockroachlib;

public interface ExceptionHandler {

        /**
         * 出现异常
         *
         * @param thread
         * @param throwable
         * @param filePath
         */
        void handlerException(Thread thread, Throwable throwable, String filePath);
    }
