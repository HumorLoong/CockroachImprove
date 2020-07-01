package com.talkweb.lxl.cockroachimprove;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

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
        requestPermission();
        initListener();
    }

    private void requestPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (!aBoolean){
                            Toast.makeText(MainActivity.this, "没有获取到手机读写权限，将不能查看详细日志信息", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "没有获取到手机读写权限，将不能查看详细日志信息", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
