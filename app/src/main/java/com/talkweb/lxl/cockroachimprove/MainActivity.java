package com.talkweb.lxl.cockroachimprove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author : LongXiaolin
 * @date   : 2020/7/1
 * Email   :528953828@qq.com
 * description :
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        findViewById(R.id.btn_go_setting).setOnClickListener(this);
        findViewById(R.id.btn_go_null).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_go_null:
                startActivity(new Intent(this,NullErrorActivity.class));
                break;
            case R.id.btn_go_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            default:
                break;
        }
    }
}
