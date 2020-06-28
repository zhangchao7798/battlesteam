package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Commodity;
import com.medical.exam.dao.CommodityMapper;
import com.medical.exam.service.CommodityService;
import org.springframework.stereotype.Service;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    private final CommodityMapper commodityMapper;

    public CommodityServiceImpl(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    @Override
    public Commodity getCommodity(Long commodityId) {
        return this.commodityMapper.selectByPrimaryKey(commodityId);
    }
}
