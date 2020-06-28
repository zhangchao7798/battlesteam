package com.medical.exam.common.interceptor;

import com.medical.exam.bean.po.Token;
import com.medical.exam.bean.po.User;
import com.medical.exam.common.exception.AuthException;
import com.medical.exam.common.holder.TokenHolder;
import com.medical.exam.service.TokenService;
import com.medical.exam.service.UserService;
import com.medical.exam.util.SpringUtil;
import com.medical.exam.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.medical.exam.common.constant.SystemConstant.ROLE_AGENT_ALLOWED_PATH;
import static com.medical.exam.common.constant.SystemConstant.ROLE_COMPANY_ALLOWED_PATH;
import static com.medical.exam.common.constant.SystemConstant.ROLE_MERCHANT_ALLOWED_PATH;
import static com.medical.exam.common.constant.SystemConstant.ROLE_NORMAL_ALLOWED_PATH;
import static com.medical.exam.common.constant.SystemConstant.ROLE_TYPE_AGENT;
import static com.medical.exam.common.constant.SystemConstant.ROLE_TYPE_COMPANY;
import static com.medical.exam.common.constant.SystemConstant.ROLE_TYPE_MERCHANT;
import static com.medical.exam.common.constant.SystemConstant.ROLE_TYPE_NORMAL;
import static com.medical.exam.common.constant.SystemConstant.TOKEN_KEY;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/01
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        TokenService tokenService = SpringUtil.getBean(TokenService.class);
        String token = request.getParameter(TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new AuthException("token不能为空");
        }
        Token t = tokenService.findByToken(token);
        if (null == t) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new AuthException("token已失效");
        }
        TokenHolder.setToken(t);
        //检查角色权限
        UserService userService = SpringUtil.getBean(UserService.class);
        User user = userService.findByUserId(t.getUserId());
        if (null != user) {
            switch (user.getRoleType()) {
                case ROLE_TYPE_NORMAL:
                    checkPermission(ROLE_NORMAL_ALLOWED_PATH, uri);
                    break;
                case ROLE_TYPE_COMPANY:
                    checkPermission(ROLE_COMPANY_ALLOWED_PATH, uri);
                    break;
                case ROLE_TYPE_MERCHANT:
                    checkPermission(ROLE_MERCHANT_ALLOWED_PATH, uri);
                    break;
                case ROLE_TYPE_AGENT:
                    checkPermission(ROLE_AGENT_ALLOWED_PATH, uri);
                    break;
                default:
                    throw new AuthException("用户权限不足");
            }
        }
        return true;
    }

    private void checkPermission(String allowedPath, String uri) {
        if (!PatternMatchUtils.simpleMatch(allowedPath, uri)) {
            throw new AuthException("用户权限不足");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        TokenHolder.remove();
    }
}
