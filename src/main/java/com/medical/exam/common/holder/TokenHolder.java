package com.medical.exam.common.holder;

import com.medical.exam.bean.po.Token;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
public class TokenHolder {
    private static final ThreadLocal<Token> TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    public static void setToken(Token token) {
        TOKEN_THREAD_LOCAL.set(token);
    }

    public static Token getToken() {
        return TOKEN_THREAD_LOCAL.get();
    }

    public static void remove() {
        TOKEN_THREAD_LOCAL.remove();
    }
}
