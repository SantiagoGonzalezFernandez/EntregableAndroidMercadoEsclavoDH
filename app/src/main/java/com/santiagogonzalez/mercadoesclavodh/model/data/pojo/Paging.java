package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Paging implements Serializable {

    @SerializedName("total")
    private Integer myIntegerTotal;

    @SerializedName("offset")
    private Integer myIntegerOffset;

    @SerializedName("limit")
    private Integer myIntegerLimit;

    public Paging() {
    }

    public Paging(Integer myIntegerTotal, Integer myIntegerOffset, Integer myIntegerLimit) {
        this.myIntegerTotal = myIntegerTotal;
        this.myIntegerOffset = myIntegerOffset;
        this.myIntegerLimit = myIntegerLimit;
    }

    public Integer getMyIntegerTotal() {
        return myIntegerTotal;
    }

    public void setMyIntegerTotal(Integer myIntegerTotal) {
        this.myIntegerTotal = myIntegerTotal;
    }

    public Integer getMyIntegerOffset() {
        return myIntegerOffset;
    }

    public void setMyIntegerOffset(Integer myIntegerOffset) {
        this.myIntegerOffset = myIntegerOffset;
    }

    public Integer getMyIntegerLimit() {
        return myIntegerLimit;
    }

    public void setMyIntegerLimit(Integer myIntegerLimit) {
        this.myIntegerLimit = myIntegerLimit;
    }
}
