package com.highgreat.sven.aop_module.login;

import android.content.Context;

public class LoginAssistant {

    static LoginAssistant instance;
    private ILogin iLogin;
    private Context applicationContext;

    public static LoginAssistant getInstance(){
        synchronized (LoginAssistant.class){
            if(instance == null){
                instance = new LoginAssistant();
            }
            return instance;
        }
    }

    public ILogin getiLogin() {
        return iLogin;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }
}
