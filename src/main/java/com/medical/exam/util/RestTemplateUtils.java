package com.medical.exam.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.medical.exam.common.annotation.logger.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/04/23
 */
@Logger
public final class RestTemplateUtils {
    private static volatile RestTemplateUtils instance;
    private static RestTemplate restTemplate;

    private RestTemplateUtils() {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converterList.add(new FastJsonHttpMessageConverter());
    }

    public static RestTemplateUtils getInstance() {
        if (null == instance) {
            synchronized (RestTemplateUtils.class) {
                if (null == instance) {
                    instance = new RestTemplateUtils();
                }
            }

        }
        return instance;
    }

    /**
     * get for object
     *
     * @param url
     * @param clasz
     * @param <T>
     * @return
     */
    public <T> T getForObject(String url, Class<T> clasz) {
        return restTemplate.getForObject(url, clasz);
    }

    /**
     * post for object
     *
     * @param url
     * @param clasz
     * @param params
     * @param <T>
     * @return
     */
    public <T> T postForObject(String url, Class<T> clasz, Map<String, Object> params) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Object>> r = new HttpEntity<>(params, headers);
        return restTemplate.postForObject(url, r, clasz);
    }

}
