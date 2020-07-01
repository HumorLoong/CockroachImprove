package com.talkweb.lxl.cockroachimprove;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : LongXiaolin
 * @date : 2020/6/29
 * Email   :528953828@qq.com
 * description :
 */
public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch swtIsShowErr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initListener();
    }

    public void initView() {
        swtIsShowErr=findViewById(R.id.swt_is_show_err);
        swtIsShowErr.setChecked(SettingManager.getInstance().getIsShowError(this, false));
    }

    public void initListener() {
        swtIsShowErr.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SettingManager.getInstance().setIsShowError(this, isChecked);
    }
}
