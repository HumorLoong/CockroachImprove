package com.talkweb.lxl.cockroachimprove;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : LongXiaolin
 * @date : 2020/6/29
 * Email   :528953828@qq.com
 * description : 空指针错误页面
 */
public class NullErrorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_null_error);
        initView();
    }


    public void initView() {
        String name=null;
        Log.i("NullErrorActivity", "initView: "+name.substring(1));
    }

}
