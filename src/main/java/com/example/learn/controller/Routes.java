package com.example.learn.controller;

/**
 * @ClassName Routes
 * @Description 路径请求地址
 * @Date 2022/11/15 16:10
 * @Author pluto
 */
public final class Routes {

    public static final String API = "/pluto/api";

    // 公开的api，不需要鉴权
    public static final String PUBLIC_API = "/pluto/public/api";

    /* --------------------  登录  ----------------------------*/

    public static final String LOGIN = PUBLIC_API + "/login";
    public static final String REGISTER = PUBLIC_API + "/register";

    public static final String LOGINOUT = PUBLIC_API + "/loginout/{token}";

    /* --------------------  账号  ----------------------------*/
    public static final String ACCOUNT_FIND_ALL = API + "/account/find/all";

    public static final String ACCOUND_ADD = API + "/account/add";
}
