/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: ErrorPageConfigurer
 * Author:   Wu
 * Date:     2018/8/10 9:20
 * Description: 错误页面配置器
 * History:
 */
package com.medical.exam.config;

import com.medical.exam.common.resp.ResultData;
import com.medical.exam.web.BaseController;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈错误页面配置器〉
 *
 * @author Wu
 * @create 2018/8/10
 * @since 1.0.0
 */
@Configuration
@RestController
public class ErrorPageConfigurer extends BaseController {
    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401"));
            registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
            registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
            registry.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "/415"));

        }

    }

    @RequestMapping(value = "/404")
    public ResultData<Boolean> go404() {
        ResultData<Boolean> resultData = new ResultData<>();

        return errorData(resultData).setMsg("服务未找到").setStatus(404).setData(false);
    }

    @RequestMapping(value = "/500")
    public ResultData<Boolean> go500() {
        ResultData<Boolean> resultData = new ResultData<>();

        return errorData(resultData).setMsg("服务暂时不可用").setStatus(500).setData(false);
    }

    @RequestMapping(value = "/401")
    public ResultData<Boolean> go401() {
        ResultData<Boolean> resultData = new ResultData<>();
        return errorData(resultData).setMsg("未授权访问").setStatus(401).setData(false);
    }

    @RequestMapping(value = "/403")
    public ResultData<Boolean> go403() {
        ResultData<Boolean> resultData = new ResultData<>();
        return errorData(resultData).setMsg("禁止访问").setStatus(403).setData(false);
    }
    @RequestMapping(value = "/400")
    public ResultData<Boolean> go400() {
        ResultData<Boolean> resultData = new ResultData<>();
        return errorData(resultData).setMsg("参数传递错误").setStatus(400).setData(false);
    }

    @RequestMapping(value = "/405")
    public ResultData<Boolean> go405() {
        ResultData<Boolean> resultData = new ResultData<>();
        return errorData(resultData).setMsg("请求方法出错").setStatus(405).setData(false);
    }
    @RequestMapping(value = "/415")
    public ResultData<Boolean> go415() {
        ResultData<Boolean> resultData = new ResultData<>();
        return errorData(resultData).setMsg("UNSUPPORTED MEDIA TYPE").setStatus(415).setData(false);
    }

    /**
     * 错误页面配置(非异常,这种配置方式是文档上推荐的,用在外部容器也OK)
     * @return
     */
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }
}
