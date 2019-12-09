package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Seller implements Serializable {

    //Este seller sale de este pedido (https://api.mercadolibre.com/sites/MLA/search?q=pelota)
    @SerializedName("id")
    private int myIntId;

    @SerializedName("power_seller_status")
    private String myStringEstatusDelVendedor;

    @SerializedName("car_dealer")
    private Boolean myBooleanVendedorDeAutos;

    @SerializedName("real_estate_agency")
    private Boolean myBooleanAgenciaInmobiliaria;

    public Seller() {
    }

    public Seller(int myIntId, String myStringEstatusDelVendedor, Boolean myBooleanVendedorDeAutos, Boolean myBooleanAgenciaInmobiliaria) {
        this.myIntId = myIntId;
        this.myStringEstatusDelVendedor = myStringEstatusDelVendedor;
        this.myBooleanVendedorDeAutos = myBooleanVendedorDeAutos;
        this.myBooleanAgenciaInmobiliaria = myBooleanAgenciaInmobiliaria;
    }

    public int getMyIntId() {
        return myIntId;
    }

    public void setMyIntId(int myIntId) {
        this.myIntId = myIntId;
    }

    public String getMyStringEstatusDelVendedor() {
        return myStringEstatusDelVendedor;
    }

    public void setMyStringEstatusDelVendedor(String myStringEstatusDelVendedor) {
        this.myStringEstatusDelVendedor = myStringEstatusDelVendedor;
    }

    public Boolean getMyBooleanVendedorDeAutos() {
        return myBooleanVendedorDeAutos;
    }

    public void setMyBooleanVendedorDeAutos(Boolean myBooleanVendedorDeAutos) {
        this.myBooleanVendedorDeAutos = myBooleanVendedorDeAutos;
    }

    public Boolean getMyBooleanAgenciaInmobiliaria() {
        return myBooleanAgenciaInmobiliaria;
    }

    public void setMyBooleanAgenciaInmobiliaria(Boolean myBooleanAgenciaInmobiliaria) {
        this.myBooleanAgenciaInmobiliaria = myBooleanAgenciaInmobiliaria;
    }
}
