package com.medical.exam.web;

import com.medical.exam.bean.vo.ReportVo;
import com.medical.exam.bean.vo.ResultReportVo;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.holder.TokenHolder;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.resp.ResultData;
import com.medical.exam.common.resp.ResultDataFactory;
import com.medical.exam.service.CompanyService;
import com.medical.exam.service.GeneService;
import com.medical.exam.service.OrderService;
import com.medical.exam.service.ResultService;
import com.medical.exam.service.UserPersonalService;
import com.medical.exam.service.UserService;
import com.medical.exam.util.MyBeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
@RestController
@RequestMapping("/api/merchant")
@Logger
public class MerchantController extends BaseController {
    private final OrderService orderService;
    private final UserService userService;
    private final CompanyService companyService;
    private final GeneService geneService;
    private final ResultService resultService;
    private final UserPersonalService userPersonalService;

    public MerchantController(OrderService orderService, UserService userService, CompanyService companyService, GeneService geneService, ResultService resultService, UserPersonalService userPersonalService) {
        this.orderService = orderService;
        this.userService = userService;
        this.companyService = companyService;
        this.geneService = geneService;
        this.resultService = resultService;
        this.userPersonalService = userPersonalService;
    }

    /**
     * @param page
     * @param pageSize
     * @param realName
     * @return
     */
    @GetMapping("/getUserPage")
    public ResultData<PageInfo> getUserPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                            String realName, @RequestParam(required = false, defaultValue = "-1") Integer roleType) {
        ResultData<PageInfo> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userService.getUserPage(page, pageSize, realName, roleType));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getUserPage:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * @param page
     * @param pageSize
     * @param orderId
     * @param status
     * @return
     */
    @GetMapping("/getOrderPage")
    public ResultData<PageInfo> getOrderPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                             String orderId,
                                             @RequestParam(required = false, defaultValue = "-1") Integer status) {
        ResultData<PageInfo> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.getOrderPage(page, pageSize, orderId, status));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getOrderPage:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * modify Order Status
     *
     * @param orderId
     * @param status
     * @return
     */
    @PutMapping("/modifyOrderStatus/{orderId}")
    public ResultData<Boolean> modifyOrderStatus(@PathVariable String orderId,
                                                 @RequestParam Integer status) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            if (null == status || status < -1) {
                return errorData(resultData).setMsg("订单状态不正确");
            }
            return normalData(resultData).setData(this.orderService.modifyOrderStatus(orderId, status));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("modifyOrderStatus:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * @param page
     * @param pageSize
     * @param companyName
     * @return
     */
    @GetMapping("/getCompanyPage")
    public ResultData<PageInfo> getCompanyPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                               String companyName) {
        ResultData<PageInfo> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.companyService.getCompanyPage(page, pageSize, companyName));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getCompanyPage:{}", e.getMessage(), e);
            return errorData(resultData);
        }

    }

    /**
     * create company
     *
     * @param companyName
     * @return
     */
    @PostMapping("/createCompany")
    public ResultData<Boolean> createCompany(@RequestParam String companyName) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.companyService.createCompany(companyName));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("createCompany:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * modify company
     *
     * @param companyName
     * @return
     */
    @PutMapping("/modifyCompany/{companyId}")
    public ResultData<Boolean> modifyCompany(@PathVariable Long companyId, @RequestParam String companyName) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.companyService.modifyCompany(companyId, companyName));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("modifyCompany:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * bind use with company
     *
     * @param userId
     * @param companyId
     * @return
     */
    @PutMapping("/bindUserWithCompany/{userId}/{companyId}")
    public ResultData<Boolean> bindUserWithCompany(@PathVariable Long userId, @PathVariable Long companyId) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userService.bindUserWithCompany(userId, companyId));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("bindUserWithCompany:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * get root genes
     *
     * @return
     */
    @GetMapping("/getRootGenes")
    public ResultData<List<?>> getRootGenes() {
        ResultData<List<?>> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.geneService.selectRootGenes());
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getRootGenes:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * get sub genes
     *
     * @param id
     * @return
     */
    @GetMapping("/getSubGenes/{id}")
    public ResultData<List<?>> getSubGenes(@PathVariable Long id) {
        ResultData<List<?>> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.geneService.selectSubGenes(id));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getSubGenes:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * create user
     *
     * @param realName
     * @param phone
     * @param password
     * @param roleType
     * @return
     */
    @PostMapping("/createUser")
    public ResultData<Long> createUser(@RequestParam String realName,
                                       @RequestParam String phone,
                                       @RequestParam String password,
                                       @RequestParam Integer roleType) {
        ResultData<Long> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userService.createUser(realName, phone, password, roleType));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("createUser:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * modify user
     *
     * @param userId
     * @param realName
     * @param phone
     * @param password
     * @param roleType
     * @return
     */
    @PutMapping("/modifyUser/{userId}")
    public ResultData<Boolean> modifyUser(@PathVariable Long userId,
                                          @RequestParam() String realName,
                                          @RequestParam String phone,
                                          @RequestParam(required = false) String password,
                                          @RequestParam Integer roleType) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.userService.modifyUser(userId, realName, phone, password, roleType));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("modifyUser:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * @param page
     * @param pageSize
     * @param companyId
     * @return
     */
    @GetMapping("/getStaffStatusPage")
    public ResultData<PageInfo> getStaffStatusPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                   @RequestParam Long companyId) {
        ResultData<PageInfo> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.orderService.findStaffStatusByCompanyId(companyId, page, pageSize));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getStaffStatusPage:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * add gene result
     *
     * @param orderId
     * @param geneId
     * @param geneSubId
     * @param geneResult
     * @return
     */
    @PutMapping("/addGeneResult/{orderId}")
    public ResultData<Boolean> addGeneResult(@PathVariable String orderId,
                                             @RequestParam Long geneId,
                                             @RequestParam Long geneSubId,
                                             @RequestParam String geneResult) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.resultService.addGeneResult(orderId, geneId, geneSubId, geneResult));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("addGeneResult:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }

    /**
     * delete gene result
     *
     * @param orderId
     * @param geneId
     * @param geneSubId
     * @return
     */
    @DeleteMapping("/delGeneResult/{orderId}")
    public ResultData<Boolean> delGeneResult(@PathVariable String orderId,
                                             @RequestParam Long geneId,
                                             @RequestParam Long geneSubId) {
        ResultData<Boolean> resultData = ResultDataFactory.getResultData();
        try {
            return normalData(resultData).setData(this.resultService.delGeneResult(orderId, geneId, geneSubId));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("delGeneResult:{}", e.getMessage(), e);
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
            reportVo.setOrder(this.orderService.getByOrderId(orderId));
            if (null != reportVo.getOrder()) {
                reportVo.setPersonal(this.userPersonalService.getPersonal(reportVo.getOrder().getUserId()));
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
                return errorData(resultData).setMsg("报告未找到,无法查看.");
            }

        } catch (Exception e) {
            LoggerHolder.getLogger().error("getReport:{}", e.getMessage(), e);
            return errorData(resultData);

        }
    }

}
