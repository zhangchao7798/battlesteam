package com.medical.exam.util;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.medical.exam.bean.po.Commodity;
import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.vo.WxPayVo;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.constant.SystemConstant;
import com.medical.exam.common.wechat.AbstractAccessToken;
import com.medical.exam.common.wechat.AbstractTemplateMessage;
import com.medical.exam.common.wechat.AbstractTemplateMessageSender;
import com.medical.exam.common.wechat.miniprogram.MiniProgramAccessToken;
import com.medical.exam.common.wechat.miniprogram.MiniProgramTemplateMessage;
import com.medical.exam.common.wechat.miniprogram.MiniProgramTemplateMessageSender;
import com.medical.exam.config.ExamWxPayConfig;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.medical.exam.common.constant.SystemConstant.APPID;
import static com.medical.exam.common.constant.SystemConstant.SECRET;

/**
 * wechat utilities
 */
public final class WechatUtils {
    /**
     * default sign type
     */
    private static final String DEFAULT_SIGN_TYPE = "MD5";
    /**
     * SUCCESS
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * FAIL
     */
    public static final String FAIL = "FAIL";
    /**
     * OK
     */
    public static final String OK = "OK";
    /**
     * the field for order id in the response of wx pay
     */
    public static final String ORDER_ID_FIELD = "out_trade_no";

    /**
     * get phone in mini program
     *
     * @param code
     * @return
     */
    public static JSONObject loginMiniProgram(String code) {
        try {
            String reqUrl = MessageFormat.format("https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code", APPID, SECRET, code);
            JSONObject returnInfo = RestTemplateUtils.getInstance().getForObject(reqUrl, JSONObject.class);
            if (LoggerHolder.getLogger().isInfoEnabled()) {
                LoggerHolder.getLogger().info(returnInfo.toJSONString());
            }
            return returnInfo;
        } catch (Exception e) {
            LoggerHolder.getLogger().error("loginMiniProgram:{}", e.getMessage(), e);
            throw e;
        }

    }

    /**
     * unified order
     *
     * @param order
     * @param commodity
     * @param openid
     * @return
     */
    public static WxPayVo unifiedOrder(Order order, Commodity commodity, String openid) throws Exception {
        WxPayVo wxPayVo = null;
        Map<String, String> data = new HashMap<String, String>(SystemConstant.INIT_SIZE);
        data.put("body", commodity.getTitle());
        data.put("out_trade_no", order.getOrderId());
        data.put("device_info", "MINI_PROGRAM");
        data.put("fee_type", "CNY");
        data.put("total_fee", getTotalFee(order.getAmount()));
        data.put("spbill_create_ip", "47.96.123.10");
        data.put("notify_url", "http://app.saiyang-hz.com/exam-api/api/pay/callback");
        data.put("trade_type", "JSAPI");
        data.put("openid", openid);
        try {
            ExamWxPayConfig config = new ExamWxPayConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> response = wxpay.unifiedOrder(data);
            LoggerHolder.getLogger().info("response:{}", response);
            String returnCode = response.get("return_code");
            String resultCode = response.get("result_code");
            if (SUCCESS.equals(returnCode) && SUCCESS.equals(resultCode)) {
                wxPayVo = new WxPayVo();
                wxPayVo.setAppId(config.getAppID());
                wxPayVo.setNonceStr(MD5Utils.getRandomMd5());
                wxPayVo.setPackageStr("prepay_id=" + response.get("prepay_id"));
                wxPayVo.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
                wxPayVo.setSignType(DEFAULT_SIGN_TYPE);
                wxPayVo.setPaySign(sign(wxPayVo, config));
            } else {
                //支付失败,暂时无需处理.
                LoggerHolder.getLogger().error("processWxPay:{}", "微信支付失败,err_code:" + response.get("err_code") + ",err_code_des:" + response.get("err_code_des"));
            }
            return wxPayVo;
        } catch (Exception e) {
            LoggerHolder.getLogger().error("processWxPay:{}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * get total fee
     *
     * @param totalAmount
     * @return
     */
    private static String getTotalFee(BigDecimal totalAmount) {

        return String.valueOf(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
    }

    /**
     * sign
     *
     * @param wxPayVo
     * @param examWxPayConfig
     * @return
     * @throws Exception
     */
    private static String sign(WxPayVo wxPayVo, ExamWxPayConfig examWxPayConfig) throws Exception {
        Map<String, String> data = new HashMap<String, String>(SystemConstant.INIT_SIZE);
        data.put("appId", wxPayVo.getAppId());
        data.put("timeStamp", wxPayVo.getTimeStamp());
        data.put("nonceStr", wxPayVo.getNonceStr());
        data.put("package", wxPayVo.getPackageStr());
        data.put("signType", wxPayVo.getSignType());
        return WXPayUtil.generateSignature(data, examWxPayConfig.getKey());
    }

    /**
     * send template message
     *
     * @param order
     * @param openid
     */
    public static void sendTemplateMessage(String openid,Order order) {
        AbstractAccessToken accessToken = MiniProgramAccessToken.getInstance();
        AbstractTemplateMessage templateMessage = new MiniProgramTemplateMessage(openid, order);
        AbstractTemplateMessageSender templateMessageSender = new MiniProgramTemplateMessageSender(accessToken, templateMessage);
        AsynWorker asynWorker = SpringUtil.getBean(AsynWorker.class);
        if (null == asynWorker) {
            templateMessageSender.send();
        } else {
            Future<String> future =asynWorker.submitTask(templateMessageSender);
            try {
                LoggerHolder.getLogger().info("sendTemplateMessage:{}",future.get());
            } catch (Exception e) {
                LoggerHolder.getLogger().error("sendTemplateMessage:{}",e.getMessage(),e);
            }
        }
    }

}
