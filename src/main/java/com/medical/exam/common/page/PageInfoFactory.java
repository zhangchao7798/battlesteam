package com.medical.exam.common.page;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/11
 */
public class PageInfoFactory {
    private static PageInfo pageInfo = new PageInfo();

    public static <T> PageInfo getPageInfo(List<T> list) {
        PageInfo<T> clonedPageInfo = pageInfo.clone();
        Page<T> page = (Page<T>) list;
        clonedPageInfo.setPageData(page.getResult());
        clonedPageInfo.setTotalRows((int) page.getTotal());
        clonedPageInfo.setPage(page.getPageNum());
        clonedPageInfo.setPageSize(page.getPageSize());
        clonedPageInfo.setTotalPages(page.getPages());
        return clonedPageInfo;
    }

    public static <T> PageInfo getPageInfo(List<T> dataList, int total, int pageNum, int pageSize, int pages) {
        PageInfo<T> clonedPageInfo = pageInfo.clone();
        clonedPageInfo.setPageData(dataList);
        clonedPageInfo.setTotalRows(total);
        clonedPageInfo.setPage(pageNum);
        clonedPageInfo.setPageSize(pageSize);
        clonedPageInfo.setTotalPages(pages);
        return clonedPageInfo;
    }
}
