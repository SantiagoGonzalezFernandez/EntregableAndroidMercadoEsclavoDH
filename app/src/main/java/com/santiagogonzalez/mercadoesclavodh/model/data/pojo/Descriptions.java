package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Descriptions implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("created")
    private String myStringFechaDeCreacion;

    @SerializedName("text")
    private String myStringTexto;

    @SerializedName("plain_text")
    private String myStringTextoDescripcion;

    public Descriptions(String myStringId, String myStringFechaDeCreacion, String myStringTexto, String myStringTextoDescripcion) {
        this.myStringId = myStringId;
        this.myStringFechaDeCreacion = myStringFechaDeCreacion;
        this.myStringTexto = myStringTexto;
        this.myStringTextoDescripcion = myStringTextoDescripcion;
    }

    public Descriptions() {
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringFechaDeCreacion() {
        return myStringFechaDeCreacion;
    }

    public void setMyStringFechaDeCreacion(String myStringFechaDeCreacion) {
        this.myStringFechaDeCreacion = myStringFechaDeCreacion;
    }

    public String getMyStringTexto() {
        return myStringTexto;
    }

    public void setMyStringTexto(String myStringTexto) {
        this.myStringTexto = myStringTexto;
    }

    public String getMyStringTextoDescripcion() {
        return myStringTextoDescripcion;
    }

    public void setMyStringTextoDescripcion(String myStringTextoDescripcion) {
        this.myStringTextoDescripcion = myStringTextoDescripcion;
    }
}
