package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("name")
    private String myStringNombre;

    public Country(String myStringId, String myStringNombre) {
        this.myStringId = myStringId;
        this.myStringNombre = myStringNombre;
    }

    public Country() {
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
