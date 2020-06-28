package com.medical.exam.service;

import com.medical.exam.bean.po.Wechat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/02
 */
public interface WechatService {
    /**
     * login mini program
     * if the phone has not registered,will do registration for it.
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    Wechat loginMiniProgram(@NotNull String code, @NotNull String encryptedData, @NotNull String iv);

    /**
     * pre-login mini program
     * @param code
     * @return
     */
    Wechat preLoginMiniProgram(@NotNull String code);

    /**
     * get by userId
     * @param userId
     * @return
     */
    Wechat getByUserId(@NonNull Long userId);
}
