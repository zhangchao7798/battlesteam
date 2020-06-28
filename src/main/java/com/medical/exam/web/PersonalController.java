package com.medical.exam.web;

import com.medical.exam.bean.po.Commodity;
import com.medical.exam.bean.po.Order;
import com.medical.exam.bean.po.Personal;
import com.medical.exam.bean.po.Wechat;
import com.medical.exam.bean.vo.ReportVo;
import com.medical.exam.bean.vo.ResultReportVo;
import com.medical.exam.bean.vo.WxPayVo;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.holder.TokenHolder;
import com.medical.exam.common.resp.ResultData;
import com.medical.exam.common.resp.ResultDataFactory;
import com.medical.exam.service.AddresseeService;
import com.medical.exam.service.CommodityService;
import com.medical.exam.service.GeneService;
import com.medical.exam.service.OrderService;
import com.medical.exam.service.ResultService;
import com.medical.exam.service.UserPersonalService;
import com.medical.exam.service.WechatService;
import com.medical.exam.util.MyBeanUtils;
import com.medical.exam.util.WechatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@RestController
@RequestMapping("/api/personal")
@Logger
public class PersonalController extends BaseController {
    private final UserPersonalService userPersonalService;
    private final CommodityService commodityService;
    private final OrderService orderService;
    private final AddresseeService addresseeService;
    private final ResultService resultService;
    private final GeneService geneService;
    private final WechatService wechatService;

    public PersonalController(UserPersonalService userPersonalService, CommodityService commodityService, OrderService orderService, AddresseeService addresseeService, ResultService resultService, GeneService geneService, WechatService wechatService) {
        this.userPersonalService = userPersonalService;
        this.commodityService = commodityService;
        this.orderService = orderService;
        this.addresseeService = addresseeService;
        this.resultService = resultService;
        this.geneService = geneService;
        this.wechatService = wechatService;
    }

    /**
     * get personal
     *
     * @return
     */
    @GetMapping("/getPersonal")
    public ResultData<Personal> getPersonal() {
        ResultData resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userPersonalService.getPersonal(TokenHolder.getToken().getUserId()));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getPersonal:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * save personal
     *
     * @param realName
     * @param age
     * @param phone
     * @param contact
     * @param contactPhone
     * @param degree
     * @param fatherDegree
     * @param motherDegree
     * @return
     */
    @PutMapping("/savePersonal")
    public ResultData<Boolean> savePersonal(@RequestParam String realName,
                                            @RequestParam Integer age,
                                            @RequestParam String phone,
                                            @RequestParam String contact,
                                            @RequestParam String contactPhone,
                                            @RequestParam(defaultValue = "0") Integer degree,
                                            @RequestParam(defaultValue = "0") Integer fatherDegree,
                                            @RequestParam(defaultValue = "0") Integer motherDegree) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userPersonalService.savePersonal(TokenHolder.getToken().getUserId(), realName, age, phone, contact, contactPhone, degree, fatherDegree, motherDegree));

        } catch (Exception e) {
            LoggerHolder.getLogger().error("savePersonal:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * get commodity
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/getCommodity/{commodityId}")
    public ResultData<Commodity> getCommodity(@PathVariable Long commodityId) {
        ResultData<Commodity> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.commodityService.getCommodity(commodityId));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getCommodity:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * get my order list
     *
     * @return
     */
    @GetMapping("/getMyOrderList")
    public ResultData<List<?>> getMyOrderList() {
        ResultData<List<?>> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.getMyOrderList(TokenHolder.getToken().getUserId()));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getMyOrderList:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * send back
     *
     * @param orderId
     * @param courierNo
     * @return
     */
    @PutMapping("/sendBack/{orderId}")
    public ResultData<Boolean> sendBack(@PathVariable String orderId, @RequestParam String courierNo) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.sendBack(orderId, courierNo));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("sendBack:{}", e.getMessage(), e);
            return normalData(resultData);
        }

    }

    /**
     * create order
     *
     * @param commodityId
     * @param remark
     * @param quantity
     * @param addressee
     * @param phone
     * @param address
     * @param amount
     * @return
     */
    @PostMapping("/createOrder/{commodityId}")
    public ResultData<Order> createOrder(@PathVariable Long commodityId,
                                         String remark,
                                         @RequestParam(defaultValue = "1") Integer quantity,
                                         @RequestParam String addressee,
                                         @RequestParam String phone,
                                         @RequestParam String address,
                                         @RequestParam BigDecimal amount) {
        ResultData<Order> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.createOrder(commodityId, remark, quantity, addressee, phone, address, amount, TokenHolder.getToken().getUserId()));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("createOrder:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * pay order
     *
     * @param orderId
     * @param code
     * @return
     */
    @PutMapping("/payOrder/{orderId}/{code}")
    public ResultData<WxPayVo> payOrder(@PathVariable String orderId, @PathVariable String code) {
        ResultData<WxPayVo> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.payOrder(orderId, code));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("payOrder:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }

    /**
     * cancel order
     *
     * @param orderId
     * @return
     */
    @PutMapping("/cancelOrder/{orderId}")
    public ResultData<Boolean> cancelOrder(@PathVariable String orderId) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            Order order = this.orderService.getByOrderId(orderId);
            if (!TokenHolder.getToken().getUserId().equals(order.getUserId())) {
                return errorData(resultData).setMsg("非本人订单无法取消.");
            }
            return normalData(resultData).setData(this.orderService.cancelOrder(orderId));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("cancelOrder:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * save addressee
     *
     * @param addressee
     * @param phone
     * @param address
     * @return
     */
    @PutMapping("/saveAddressee")
    public ResultData<Boolean> saveAddressee(@RequestParam String addressee, @RequestParam String phone, @RequestParam String address) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.addresseeService.saveAddressee(TokenHolder.getToken().getUserId(), addressee, phone, address));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("saveAddressee:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }

    /**
     * get report
     *
     * @param orderId
     * @return
     */
    @GetMapping("/getReport/{orderId}")
    public ResultData<ReportVo> getReport(@PathVariable String orderId) {
        ResultData<ReportVo> resultData = ResultDataFactory.getResultData();
        try {
            ReportVo reportVo = new ReportVo();
            reportVo.setPersonal(this.userPersonalService.getPersonal(TokenHolder.getToken().getUserId()));
            reportVo.setOrder(this.orderService.getByOrderId(orderId));
            if (null != reportVo.getOrder() && reportVo.getOrder().getUserId().equals(TokenHolder.getToken().getUserId())) {
                List<ResultReportVo> resultReportVos = this.resultService.getResults(orderId).stream().map(r -> {
                    ResultReportVo resultReportVo = new ResultReportVo();
                    MyBeanUtils.copyPropertiesIgnoreNull(r, resultReportVo);
                    resultReportVo.setGeneText(this.geneService.findGeneById(r.getGeneId()).getGeneName());
                    resultReportVo.setGeneSubText(this.geneService.findGeneById(r.getGeneSubId()).getGeneName());
                    return resultReportVo;
                }).collect(Collectors.toList());


                reportVo.setResult(resultReportVos);
                return normalData(resultData).setData(reportVo);
            } else {
                return errorData(resultData).setMsg("非本人报告,无法查看.");
            }

        } catch (Exception e) {
            LoggerHolder.getLogger().error("getReport:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }

    /**
     * get order
     *
     * @param orderId
     * @return
     */
    @GetMapping("/getOrder/{orderId}")
    public ResultData<Order> getOrder(@PathVariable String orderId) {
        ResultData<Order> resultData = ResultDataFactory.getResultData();
        try {
            Order order = this.orderService.getByOrderId(orderId);
            if (null == order) {
                return errorData(resultData).setMsg("订单不存在");
            }
            if (!TokenHolder.getToken().getUserId().equals(order.getUserId())) {
                return errorData(resultData).setMsg("非本人订单,无法查看.");
            }
            return normalData(resultData).setData(order);
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getOrder:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }

    /**
     * send template message
     * @param orderId
     * @return
     */
    @PostMapping("/sendTemplateMessage/{orderId}")
    public ResultData<Boolean> sendTemplateMessage(@PathVariable String orderId) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            Order order = this.orderService.getByOrderId(orderId);
            if (null == order) {
                return errorData(resultData).setMsg("订单不存在");
            }
            if (!TokenHolder.getToken().getUserId().equals(order.getUserId())) {
                return errorData(resultData).setMsg("非本人订单,无法查看.");
            }
            Wechat wechat=this.wechatService.getByUserId(TokenHolder.getToken().getUserId());
            WechatUtils.sendTemplateMessage(wechat.getOpenId(),this.orderService.getByOrderId(orderId));
            return normalData(resultData);
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getOrder:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }


}
