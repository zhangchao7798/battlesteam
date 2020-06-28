/**
 * Copyright (C), 2015-2019
 * FileName: AuthException
 * Author:   Wu
 * Date:     2019/7/2 8:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xingfudeshi@gmail.com
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

    protected AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
