package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attributes implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("name")
    private String myStringNombre;

    @SerializedName("value_name")
    private String myStringDescripcionDelNombre;

    public Attributes() {
    }

    public Attributes(String myStringId, String myStringNombre, String myStringDescripcionDelNombre) {
        this.myStringId = myStringId;
        this.myStringNombre = myStringNombre;
        this.myStringDescripcionDelNombre = myStringDescripcionDelNombre;
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

    public String getMyStringDescripcionDelNombre() {
        return myStringDescripcionDelNombre;
    }

    public void setMyStringDescripcionDelNombre(String myStringDescripcionDelNombre) {
        this.myStringDescripcionDelNombre = myStringDescripcionDelNombre;
    }
}
