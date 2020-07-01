package com.talkweb.lxl.cockroachlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : LongXiaolin
 * @date : 2020/6/24
 * Email   :528953828@qq.com
 * description :
 */
public class EmptyActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, EmptyActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(500);
        finish();
    }
}
