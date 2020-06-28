package com.medical.exam.service;

import com.medical.exam.bean.po.Commodity;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
public interface CommodityService {
    /**
     * get commodity
     * @param commodityId
     * @return
     */
    Commodity getCommodity(Long commodityId);
}
