package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("name")
    private String myStringNombre;

    public City(String myStringId, String myStringNombre) {
        this.myStringId = myStringId;
        this.myStringNombre = myStringNombre;
    }

    public City() {
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringNombre() {
        return myStringNombre;
    }

    public void setMyStringNombre(String myStringNombre) {
        this.myStringNombre = myStringNombre;
    }
}
