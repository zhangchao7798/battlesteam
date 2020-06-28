package com.medical.exam.web;

import javax.validation.constraints.NotNull;

import com.medical.exam.bean.po.Token;
import com.medical.exam.bean.po.User;
import com.medical.exam.bean.po.Wechat;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.resp.ResultData;
import com.medical.exam.common.resp.ResultDataFactory;
import com.medical.exam.service.TokenService;
import com.medical.exam.service.UserService;
import com.medical.exam.service.WechatService;
import com.medical.exam.util.MD5Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.medical.exam.common.constant.SystemConstant.MSG_LOGIN_BY_MINIPROGRAM_FAILED;
import static com.medical.exam.common.constant.SystemConstant.MSG_LOGIN_BY_PWD_FAILED;


/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
@Logger
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;
    private final WechatService wechatService;

    public AuthController(UserService userService, TokenService tokenService, WechatService wechatService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.wechatService = wechatService;
    }

    /**
     * login by pwd
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/loginByPwd")
    public ResultData<Token> login(@RequestParam String phone, @RequestParam String password) {
        ResultData<Token> resultData = ResultDataFactory.getResultData();
        try {
            User user = userService.loginByPwd(phone, password);
            if (null == user) {
                return errorData(resultData).setMsg(MSG_LOGIN_BY_PWD_FAILED);
            }
            return normalData(resultData).setData(signToken(user.getUserId(), user.getRoleType()));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("loginByPwd:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * login mini program
     *
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    @PostMapping("/loginMiniProgram")
    public ResultData<Token> loginMiniProgram(@RequestParam("code") String code,
                                              @RequestParam("encryptedData") String encryptedData,
                                              @RequestParam("iv") String iv) {
        ResultData<Token> resultData = ResultDataFactory.getResultData();

        try {
            Wechat wechat = wechatService.loginMiniProgram(code, encryptedData, iv);
            if (null == wechat) {
                return errorData(resultData).setMsg(MSG_LOGIN_BY_MINIPROGRAM_FAILED);
            }
            User user = this.userService.findByUserId(wechat.getUserId());
            return normalData(resultData).setData(signToken(user.getUserId(), user.getRoleType()));

        } catch (Exception e) {
            LoggerHolder.getLogger().error("loginMiniProgram:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * pre login mini program
     *
     * @param code
     * @return
     */
    @PostMapping("/preLoginMiniProgram")
    public ResultData<Token> preLoginMiniProgram(@RequestParam("code") String code) {
        ResultData<Token> resultData = ResultDataFactory.getResultData();
        try {
            Wechat wechat = wechatService.preLoginMiniProgram(code);
            if (null == wechat) {
                return errorData(resultData).setMsg(MSG_LOGIN_BY_MINIPROGRAM_FAILED);
            }
            User user = this.userService.findByUserId(wechat.getUserId());
            return normalData(resultData).setData(signToken(user.getUserId(), user.getRoleType()));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("preLoginMiniProgram:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * sign a new token
     *
     * @param userId
     * @return
     */
    private Token signToken(@NotNull Long userId, @NotNull Integer roleType) {
        Token token = new Token();
        token.setUserId(userId).setRoleType(roleType).setToken(MD5Utils.getRandomMd5());
        if (this.tokenService.saveOrUpdate(token)) {
            return token;
        }
        return null;
    }


}
