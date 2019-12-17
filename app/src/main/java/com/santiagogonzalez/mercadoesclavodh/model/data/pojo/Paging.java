package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import java.io.Serializable;

public class Paging implements Serializable {

    private Integer total;

    private Integer offset;

    private Integer limit;

    public Paging() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Paging(Integer total, Integer offset, Integer limit) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }
}
