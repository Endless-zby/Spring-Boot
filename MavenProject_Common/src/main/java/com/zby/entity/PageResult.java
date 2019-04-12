package com.zby.entity;

import java.util.List;

public class PageResult<T> {
    private Long total ;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
    public PageResult() {
    }

    private List<T> rows ;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }




}
