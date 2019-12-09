package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor.searchLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Neighborhood implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("name")
    private String myStringnombre;

    public Neighborhood() {
    }

    public Neighborhood(String myStringId, String myStringnombre) {
        this.myStringId = myStringId;
        this.myStringnombre = myStringnombre;
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringnombre() {
        return myStringnombre;
    }

    public void setMyStringnombre(String myStringnombre) {
        this.myStringnombre = myStringnombre;
    }
}
