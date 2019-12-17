package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DescripcioDeProducto implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("plain_text")
    private String myStringTextoDescripcion;

    public DescripcioDeProducto() {
    }

    public DescripcioDeProducto(String myStringId, String myStringTextoDescripcion) {
        this.myStringId = myStringId;
        this.myStringTextoDescripcion = myStringTextoDescripcion;
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringTextoDescripcion() {
        return myStringTextoDescripcion;
    }

    public void setMyStringTextoDescripcion(String myStringTextoDescripcion) {
        this.myStringTextoDescripcion = myStringTextoDescripcion;
    }
}
