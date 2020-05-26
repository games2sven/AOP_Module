package com.highgreat.sven.aop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.highgreat.sven.aop_module.annotation.BehaviorTrace;
import com.highgreat.sven.aop_module.annotation.LoginFilter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @BehaviorTrace("摇一摇")
    @LoginFilter()
    public void mShake(View view){
        startActivity(new Intent(this, shakeActivity.class));
    }

    //语音消息
    public void mAudio(View view) {

    }

    //视频通话
    public void mVideo(View view) {
    }

    //发表说说
    public void saySomething(View view) {
    }

    //退出登录
    public void clearLoginInfo(View view) {
        SharePreferenceUtil.clearSharePref(SharePreferenceUtil.IS_LOGIN, this);
        Toast.makeText(this, "退出登录！", Toast.LENGTH_SHORT).show();
    }
}
