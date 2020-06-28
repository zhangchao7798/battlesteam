package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Result;
import com.medical.exam.common.exception.ServiceException;
import com.medical.exam.dao.ResultMapper;
import com.medical.exam.service.ResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
@Service
public class ResultServiceImpl implements ResultService {
    private final ResultMapper resultMapper;

    public ResultServiceImpl(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Boolean addGeneResult(String orderId, Long geneId, Long geneSubId, String geneResult) {
        try {
            Result result = new Result();
            result.setOrderId(orderId).setGeneId(geneId).setGeneSubId(geneSubId);
            Result existResult = this.resultMapper.selectOne(result);
            if (null != existResult) {
                existResult.setGeneResult(geneResult);
                return this.resultMapper.updateByPrimaryKeySelective(existResult) > 0;
            }
            result.setGeneResult(geneResult);
            return this.resultMapper.insertSelective(result) > 0;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public Boolean delGeneResult(String orderId, Long geneId, Long geneSubId) {
        Result result = new Result();
        result.setOrderId(orderId).setGeneId(geneId).setGeneSubId(geneSubId);
        return this.resultMapper.delete(result)>0;
    }

    @Override
    public List<Result> getResults(String orderId) {
        Result result = new Result();
        result.setOrderId(orderId);
        return this.resultMapper.select(result);
    }
}
