package com.medical.exam.service;

import com.medical.exam.bean.po.Token;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
public interface TokenService {
    /**
     * find by token
     * @param token
     * @return
     */
    Token findByToken(String token);

    /**
     * find by userId
     * @param userId
     * @return
     */
    Token findByUserId(Long userId);

    /**
     * save or update
     * @return
     */
    Boolean saveOrUpdate(Token token);
}
