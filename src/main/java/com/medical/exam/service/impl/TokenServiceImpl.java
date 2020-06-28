package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Token;
import com.medical.exam.dao.TokenMapper;
import com.medical.exam.service.TokenService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
@Service
public class TokenServiceImpl implements TokenService {
    private final TokenMapper tokenMapper;

    public TokenServiceImpl(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public Token findByToken(String token) {
        Token t = new Token();
        t.setToken(token);
        return this.tokenMapper.selectOne(t);
    }

    @Override
    public Token findByUserId(Long userId) {
        return tokenMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Boolean saveOrUpdate(@NotNull Token token) {
        if (null == token.getUserId()) {
            throw new IllegalArgumentException("userId不能为空");
        }
        Token existToken = findByUserId(token.getUserId());
        return null == existToken ? this.tokenMapper.insertSelective(token) > 0 : this.tokenMapper.updateByPrimaryKeySelective(existToken.setToken(token.getToken())) > 0;
    }
}
