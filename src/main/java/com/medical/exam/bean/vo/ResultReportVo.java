/**
 * Copyright (C), 2015-2019
 * FileName: ResultReportVo
 * Author:   Wu
 * Date:     2019/7/19 23:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package com.medical.exam.bean.vo;

import com.medical.exam.bean.po.Result;
/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/19
 */
public class ResultReportVo extends Result {
    private String geneText;
    private String geneSubText;

    public String getGeneText() {
        return geneText;
    }

    public ResultReportVo setGeneText(String geneText) {
        this.geneText = geneText;
        return this;
    }

    public String getGeneSubText() {
        return geneSubText;
    }

    public ResultReportVo setGeneSubText(String geneSubText) {
        this.geneSubText = geneSubText;
        return this;
    }

    @Override
    public String toString() {
        return "ResultReportVo{" +
            "geneText='" + geneText + '\'' +
            ", geneSubText='" + geneSubText + '\'' +
            '}';
    }
}
