package com.system.core.bean;

import com.system.core.impl.FrameworkPageImpl;

import java.util.List;

public interface FrameworkPage<T> {
    static <T> FrameworkPage<T> getFrameworkPage(FrameworkPageable frameworkPageable, List<T> content) {
        FrameworkPage<T> result = new FrameworkPageImpl();
        result.setContent(content);
        result.setPageNo(frameworkPageable.getPageNo());
        result.setPageSize(frameworkPageable.getPageSize());
        result.setTotalPage(frameworkPageable.getTotalPage());
        result.setTotalRecord(frameworkPageable.getTotalRecord());
        return result;
    }

    int getPageNo();

    void setPageNo(int pageNo);

    int getPageSize();

    void setPageSize(int pageSize);

    long getTotalRecord();

    void setTotalRecord(long totalRecord);

    int getTotalPage();

    void setTotalPage(int totalPage);

    List<T> getContent();

    void setContent(List<T> content);

}
