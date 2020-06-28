package com.medical.exam.common.constant;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
public class SystemConstant {
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 小程序 appid
     */
    public static final String APPID = "wxffc5f93a7cd0246b";
    /**
     * 小程序 secret
     */
    public static final String SECRET = "f11cad9a2bf806bdde77167742a1dddc";
    /**
     * token key
     */
    public static final String TOKEN_KEY = "token";
    /**
     * default date format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int INIT_SIZE = 8;
    /**
     * 普通角色
     */
    public static final int ROLE_TYPE_NORMAL = 0;
    /**
     * 企业角色
     */
    public static final int ROLE_TYPE_COMPANY = 1;
    /**
     * 商家角色
     */
    public static final int ROLE_TYPE_MERCHANT = 2;
    /**
     * 代理角色
     */
    public static final int ROLE_TYPE_AGENT = 3;

    /**
     * 普通角色允许访问的接口路径
     */
    public static final String ROLE_NORMAL_ALLOWED_PATH = "/api/personal/**";
    /**
     * 企业角色允许访问的接口路径
     */
    public static final String ROLE_COMPANY_ALLOWED_PATH = "/api/company/**";
    /**
     * 商家角色允许访问的接口路径
     */
    public static final String ROLE_MERCHANT_ALLOWED_PATH = "/api/merchant/**";
    /**
     * 代理角色允许访问的接口路径
     */
    public static final String ROLE_AGENT_ALLOWED_PATH = "/api/agent/**";

    public static final String MSG_LOGIN_BY_PWD_FAILED = "用户登陆失败,请检查账号或密码";
    public static final String MSG_LOGIN_BY_MINIPROGRAM_FAILED = "小程序登陆失败,请稍后再试.";
}
