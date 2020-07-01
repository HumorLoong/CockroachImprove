package com.talkweb.lxl.cockroachimprove;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : LongXiaolin
 * @date : 2020/6/29
 * Email   :528953828@qq.com
 * description :
 */
public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Switch swtIsShowErr;
    private Switch swtIsInstall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initListener();
    }

    public void initView() {
        swtIsShowErr = findViewById(R.id.swt_is_show_err);
        swtIsInstall = findViewById(R.id.swt_is_install);
        swtIsShowErr.setChecked(SettingManager.getInstance().getIsShowError(this, false));
        swtIsInstall.setChecked(SettingManager.getInstance().getIsInstall(this, true));
        ((LinearLayout)swtIsShowErr.getParent()).setVisibility(SettingManager.getInstance().getIsInstall(this, true) ? View.VISIBLE : View.GONE);
    }

    public void initListener() {
        swtIsShowErr.setOnCheckedChangeListener(this);
        swtIsInstall.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.swt_is_show_err) {
            SettingManager.getInstance().setIsShowError(this, isChecked);
        } else {
            ((LinearLayout)swtIsShowErr.getParent()).setVisibility(isChecked ? View.VISIBLE : View.GONE);
            SettingManager.getInstance().setIsInstall(this, isChecked);
            Toast.makeText(this, "请退出应用并清理后台再进行操作", Toast.LENGTH_SHORT).show();
        }
    }
}
