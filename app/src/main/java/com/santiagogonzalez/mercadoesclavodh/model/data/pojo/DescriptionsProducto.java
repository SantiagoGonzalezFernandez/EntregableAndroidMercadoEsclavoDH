package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DescriptionsProducto implements Serializable {

    @SerializedName("id")
    private String myStringId;

    public DescriptionsProducto() {
    }

    public DescriptionsProducto(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }
}
