package com.medical.exam.service;

import com.medical.exam.bean.po.Result;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
public interface ResultService {
    /**
     * add gene result
     * @param orderId
     * @param geneId
     * @param geneSubId
     * @param geneResult
     * @return
     */
    Boolean addGeneResult(String orderId,Long geneId,Long geneSubId,String geneResult);

    /**
     * delete gene result
     * @param orderId
     * @param geneId
     * @param geneSubId
     * @return
     */
    Boolean delGeneResult(String orderId,Long geneId,Long geneSubId);

    /**
     * get results
     * @param orderId
     * @return
     */
    List<Result> getResults(String orderId);
}
