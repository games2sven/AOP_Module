package com.highgreat.sven.aop_module.login;

import android.content.Context;

public class LoginSDK {
    private static LoginSDK instance;

    private LoginSDK() {}
    public static LoginSDK getInstance(){
        if(instance == null){
            synchronized (LoginSDK.class){
                if(instance == null){
                    instance = new LoginSDK();
                }
            }
        }
        return instance;
    }

    public void init(Context context,ILogin iLogin){
        LoginAssistant.getInstance().setApplicationContext(context);
        LoginAssistant.getInstance().setiLogin(iLogin);
    }

}
