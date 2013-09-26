/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

/**
 * 登录状态
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public enum LoginStatus {

    /** 用户名或密码错误! */
    LOGIN_ERROR("login_error", "用户名或密码错误!"),

    /** 验证码错误! */
    ERROR_CODE("error_code", "验证码错误!"),

    /** 退出成功! */
    LOGOUT("logout", "退出成功!"),

    /** 您的账号已在别的地方登录 */
    RELOGIN("relogin", "您的账号已在别的地方登录"),

    /** 登录超时 */
    TIMEOUT("timeout", "登录超时"),

    /** 该用户名不存在 */
    NO_USER("no_user", "该用户名不存在");

    String code;
    String name;

    LoginStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static LoginStatus getLoginStatus(String code) {
        for (LoginStatus status : LoginStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
