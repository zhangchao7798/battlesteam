package com.medical.exam.bean.vo;

import java.io.Serializable;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/10
 */
public class WxPayVo implements Serializable {
    /**
     * 是否需要是否,订单金额为0元时,无需唤起微信支付.
     */
    private Boolean needPay = true;
    /**
     * 小程序ID
     */
    private String appId;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机串
     */
    private String nonceStr;
    /**
     * 数据包
     */
    private String packageStr;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 签名
     */
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public WxPayVo setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public WxPayVo setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public WxPayVo setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public WxPayVo setPackageStr(String packageStr) {
        this.packageStr = packageStr;
        return this;
    }

    public String getSignType() {
        return signType;
    }

    public WxPayVo setSignType(String signType) {
        this.signType = signType;
        return this;
    }

    public String getPaySign() {
        return paySign;
    }

    public WxPayVo setPaySign(String paySign) {
        this.paySign = paySign;
        return this;
    }

    public Boolean getNeedPay() {
        return needPay;
    }

    public WxPayVo setNeedPay(Boolean needPay) {
        this.needPay = needPay;
        return this;
    }

    @Override
    public String toString() {
        return "WxPayVo{" +
            "needPay=" + needPay +
            ", appId='" + appId + '\'' +
            ", timeStamp='" + timeStamp + '\'' +
            ", nonceStr='" + nonceStr + '\'' +
            ", packageStr='" + packageStr + '\'' +
            ", signType='" + signType + '\'' +
            ", paySign='" + paySign + '\'' +
            '}';
    }
}