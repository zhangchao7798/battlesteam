package com.medical.exam.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.medical.exam.common.constant.SystemConstant;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/05/17
 */
public class ExamWxPayConfig implements WXPayConfig {

    private byte[] certData;

    public ExamWxPayConfig() throws Exception {
        ClassPathResource resource = new ClassPathResource("wxpay/apiclient_cert.p12");

        try (InputStream certStream = resource.getInputStream()) {
            this.certData = IOUtils.toByteArray(certStream);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String getAppID() {
        return SystemConstant.APPID;
    }

    @Override
    public String getMchID() {
        return "1544814761";
    }

    @Override
    public String getKey() {
        return "3c810e70004953cdaab5e699b0fc2318";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}