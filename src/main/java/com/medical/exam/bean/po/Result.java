package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
@Table(name = "sys_gene_result")
public class Result extends BaseEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private String orderId;
    private Long geneId;
    private Long geneSubId;
    private String geneResult;


    public Long getId() {
        return id;
    }

    public Result setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public Result setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getGeneId() {
        return geneId;
    }

    public Result setGeneId(Long geneId) {
        this.geneId = geneId;
        return this;
    }

    public Long getGeneSubId() {
        return geneSubId;
    }

    public Result setGeneSubId(Long geneSubId) {
        this.geneSubId = geneSubId;
        return this;
    }

    public String getGeneResult() {
        return geneResult;
    }

    public Result setGeneResult(String geneResult) {
        this.geneResult = geneResult;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
            "id=" + id +
            ", orderId='" + orderId + '\'' +
            ", geneId=" + geneId +
            ", geneSubId=" + geneSubId +
            ", geneResult='" + geneResult + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (!id.equals(result.id)) return false;
        if (orderId != null ? !orderId.equals(result.orderId) : result.orderId != null) return false;
        if (geneId != null ? !geneId.equals(result.geneId) : result.geneId != null) return false;
        if (geneSubId != null ? !geneSubId.equals(result.geneSubId) : result.geneSubId != null) return false;
        return geneResult != null ? geneResult.equals(result.geneResult) : result.geneResult == null;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
