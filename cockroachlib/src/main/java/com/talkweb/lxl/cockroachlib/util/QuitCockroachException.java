package com.talkweb.lxl.cockroachlib.util;


/**
 * @author : LongXiaolin
 * @date   : 2020/6/22
 * Email   :528953828@qq.com
 * description : 
 */
public final class QuitCockroachException extends RuntimeException {
    public QuitCockroachException(String message) {
        super(message);
    }
}