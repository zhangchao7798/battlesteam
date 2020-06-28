/**
 * Copyright (C), 2018-2018, truthai.cn
 * FileName: PageInfo
 * Author:   Wu
 * Date:     2018/8/22 14:00
 * Description:
 * History:
 */
package com.medical.exam.common.page;

import com.github.pagehelper.Page;
import com.medical.exam.common.annotation.logger.LoggerHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
public class PageInfo<T> implements Serializable, Cloneable {
    /**
     * 总条数
     */
    private Integer totalRows;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 每页显示多少条
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页数据列表
     */
    private List<T> pageData = new ArrayList<>();

    protected PageInfo() {
    }

    public PageInfo(List<T> list) {
        Page<T> page = (Page<T>) list;
        setPageData(page.getResult());
        setTotalRows((int) page.getTotal());
        setPage(page.getPageNum());
        setPageSize(page.getPageSize());
        setTotalPages(page.getPages());

    }

    public PageInfo(List<T> dataList, int total, int pageNum, int pageSize, int pages) {
        setPageData(dataList);
        setTotalRows(total);
        setPage(pageNum);
        setPageSize(pageSize);
        setTotalPages(pages);

    }


    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
            "totalRows=" + totalRows +
            ", totalPages=" + totalPages +
            ", pageSize=" + pageSize +
            ", page=" + page +
            ", pageData=" + pageData +
            '}';
    }

    @Override
    protected PageInfo clone() {
        PageInfo pageInfo = null;
        try {
            pageInfo = (PageInfo) super.clone();
            pageInfo.setPageData((List) ((ArrayList)this.getPageData()).clone());
        } catch (CloneNotSupportedException e) {
            LoggerHolder.getLogger().error("clone page info error:{} ", e.getMessage(),e);
        }

        return pageInfo;
    }
}
