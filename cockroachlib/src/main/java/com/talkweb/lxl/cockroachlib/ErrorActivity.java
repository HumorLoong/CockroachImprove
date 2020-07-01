package com.talkweb.lxl.cockroachlib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.talkweb.lxl.cockroachlib.util.Constants;
import com.talkweb.lxl.cockroachlib.util.FileUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : LongXiaolin
 * @date : 2020/6/24
 * Email   :528953828@qq.com
 * description : 发生非异常问题后的崩溃错误页面
 */
public class ErrorActivity extends AppCompatActivity {
    private TextView tvErrorMsg;
    private TextView tvErrorMsgDetail;

    /**
     * 错误页面
     *
     * @param context  上下文
     * @param errStr   错误信息
     * @param filePath 信息错误信息文件地址
     */
    public static void start(Context context, String errStr, String filePath) {
        Intent starter = new Intent(context, ErrorActivity.class);
        starter.putExtra(Constants.BUNDLE_DATA, errStr);
        starter.putExtra(Constants.BUNDLE_PATH, filePath);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_error);
        initView();
        initData();
    }

    public void initView() {
        tvErrorMsg = findViewById(R.id.tv_err_msg);
        tvErrorMsgDetail = findViewById(R.id.tv_err_msg_detail);
        tvErrorMsg.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvErrorMsgDetail.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void initData() {
        if (getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra(Constants.BUNDLE_DATA))) {
            tvErrorMsg.setText(getIntent().getStringExtra(Constants.BUNDLE_DATA));
        }
        if (getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra(Constants.BUNDLE_PATH))) {
            String filePath = getIntent().getStringExtra(Constants.BUNDLE_PATH);
            String errDetail = FileUtil.readFile2Str(filePath,getPackageName());
            tvErrorMsgDetail.setText(Html.fromHtml(errDetail.replaceAll("\n","<br>")));
        }
    }
}
