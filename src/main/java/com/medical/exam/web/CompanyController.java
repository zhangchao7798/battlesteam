package com.medical.exam.web;

import com.medical.exam.bean.po.User;
import com.medical.exam.common.annotation.logger.Logger;
import com.medical.exam.common.annotation.logger.LoggerHolder;
import com.medical.exam.common.holder.TokenHolder;
import com.medical.exam.common.page.PageInfo;
import com.medical.exam.common.resp.ResultData;
import com.medical.exam.common.resp.ResultDataFactory;
import com.medical.exam.service.CompanyService;
import com.medical.exam.service.OrderService;
import com.medical.exam.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
@RestController
@RequestMapping("/api/company")
@Logger
public class CompanyController extends BaseController {
    private final CompanyService companyService;
    private final UserService userService;
    private final OrderService orderService;


    public CompanyController(CompanyService companyService, UserService userService, OrderService orderService) {
        this.companyService = companyService;
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * get staff status page
     *
     * @return
     */
    @GetMapping("/getStaffStatusPage")
    public ResultData<PageInfo> getStaffStatusPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        ResultData<PageInfo> resultData = ResultDataFactory.getResultData();
        try {
            User user = this.userService.findByUserId(TokenHolder.getToken().getUserId());
            return normalData(resultData).setData(this.orderService.findStaffStatusByCompanyId(user.getCompanyId(), page, pageSize));
        } catch (Exception e) {
            LoggerHolder.getLogger().error("getStaffStatusPage:{}", e.getMessage(), e);
            return errorData(resultData);
        }
    }
}
