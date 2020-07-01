package com.talkweb.lxl.cockroachimprove;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author : LongXiaolin
 * @date : 2020/6/29
 * Email   :528953828@qq.com
 * description :
 */
public class SettingManager {
    /**
     * 设置sp的名称
     */
    private static final String SP_SETTING_NAME="sp_s_setting_name";

    /**
     * key 是否显示错误信息
     */
    private static final String SP_KEY_IS_SHOW_ERROR="sp_key_is_show_error";


    private SettingManager() {
    }

    public static synchronized SettingManager getInstance() {
        return SettingManagerHolder.INSTANCE;
    }

    private static class SettingManagerHolder {
        private static final SettingManager INSTANCE =new SettingManager();
    }

    /**
     * 设置是否显示错误信息
     * @param context
     * @param isShowError
     */
    public void setIsShowError(Context context,boolean isShowError){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTING_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(SP_KEY_IS_SHOW_ERROR,isShowError);
        edit.apply();
    }


    /**
     * 获取是否显示错误信息
     * @param context
     * @param defValue
     * @return
     */
    public boolean getIsShowError(Context context,boolean defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTING_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(SP_KEY_IS_SHOW_ERROR, defValue);
    }
}
