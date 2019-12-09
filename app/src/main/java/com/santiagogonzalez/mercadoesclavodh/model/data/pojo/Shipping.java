package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shipping implements Serializable {

    @SerializedName("mode")
    private String myStringModo;

    @SerializedName("dimensions")
    private String myStringDimenciones;

    @SerializedName("local_pick_up")
    private Boolean myBooleanRecogidaEnElLocal;

    @SerializedName("free_shipping")
    private Boolean myBooleanEnvioGratis;

    @SerializedName("logistic_type")
    private String myStringTipoDeLogistica;

    @SerializedName("store_pick_up")
    private Boolean myBooleanRecogidaEnLaTienda;

    public Shipping() {
    }

    public Shipping(String myStringModo, String myStringDimenciones, Boolean myBooleanRecogidaEnElLocal, Boolean myBooleanEnvioGratis, String myStringTipoDeLogistica, Boolean myBooleanRecogidaEnLaTienda) {
        this.myStringModo = myStringModo;
        this.myStringDimenciones = myStringDimenciones;
        this.myBooleanRecogidaEnElLocal = myBooleanRecogidaEnElLocal;
        this.myBooleanEnvioGratis = myBooleanEnvioGratis;
        this.myStringTipoDeLogistica = myStringTipoDeLogistica;
        this.myBooleanRecogidaEnLaTienda = myBooleanRecogidaEnLaTienda;
    }

    public String getMyStringModo() {
        return myStringModo;
    }

    public void setMyStringModo(String myStringModo) {
        this.myStringModo = myStringModo;
    }

    public String getMyStringDimenciones() {
        return myStringDimenciones;
    }

    public void setMyStringDimenciones(String myStringDimenciones) {
        this.myStringDimenciones = myStringDimenciones;
    }

    public Boolean getMyBooleanRecogidaEnElLocal() {
        return myBooleanRecogidaEnElLocal;
    }

    public void setMyBooleanRecogidaEnElLocal(Boolean myBooleanRecogidaEnElLocal) {
        this.myBooleanRecogidaEnElLocal = myBooleanRecogidaEnElLocal;
    }

    public Boolean getMyBooleanEnvioGratis() {
        return myBooleanEnvioGratis;
    }

    public void setMyBooleanEnvioGratis(Boolean myBooleanEnvioGratis) {
        this.myBooleanEnvioGratis = myBooleanEnvioGratis;
    }

    public String getMyStringTipoDeLogistica() {
        return myStringTipoDeLogistica;
    }

    public void setMyStringTipoDeLogistica(String myStringTipoDeLogistica) {
        this.myStringTipoDeLogistica = myStringTipoDeLogistica;
    }

    public Boolean getMyBooleanRecogidaEnLaTienda() {
        return myBooleanRecogidaEnLaTienda;
    }

    public void setMyBooleanRecogidaEnLaTienda(Boolean myBooleanRecogidaEnLaTienda) {
        this.myBooleanRecogidaEnLaTienda = myBooleanRecogidaEnLaTienda;
    }
}
