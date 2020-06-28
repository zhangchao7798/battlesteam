package com.medical.exam.util;

import java.util.Date;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/05/21
 */
public class OrderUtils {
    /**
     * generate time based order id
     * @param userId
     * @return
     */
    public static String generateTimeBasedOrderId(Long userId){
        return DateUtils.format(new Date(), "yyyyMMddhhmmssSSS")+userId;
    }


}
