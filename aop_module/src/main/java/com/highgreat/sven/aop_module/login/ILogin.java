package com.highgreat.sven.aop_module.login;

import android.content.Context;

public interface ILogin {

    void login(Context applicationContext,int value);

    /**
     * 判断是否登录
     * @param applicationContext
     * @return
     */
    boolean isLogin(Context applicationContext);

    /**
     * 清除登录状态
     * @param applicationContext
     */
    void clearLoginStatus(Context applicationContext);

}
