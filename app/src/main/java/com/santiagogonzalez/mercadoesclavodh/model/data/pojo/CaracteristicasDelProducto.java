package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CaracteristicasDelProducto implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("title")
    private String myStringTitulo;

    @SerializedName("price")
    private String myStringPrecio;

    @SerializedName("available_quantity")
    private String myStringCantidadDisponible;

    @SerializedName("sold_quantity")
    private String myStringCantidadVendida;

    @SerializedName("condition")
    private String myStringCondicion;

    @SerializedName("geolocation")
    private Geolocation myGeolocation;

    public CaracteristicasDelProducto() {
    }

    public CaracteristicasDelProducto(String myStringId, String myStringTitulo, String myStringPrecio, String myStringCantidadDisponible, String myStringCantidadVendida, String myStringCondicion, Geolocation myGeolocation) {
        this.myStringId = myStringId;
        this.myStringTitulo = myStringTitulo;
        this.myStringPrecio = myStringPrecio;
        this.myStringCantidadDisponible = myStringCantidadDisponible;
        this.myStringCantidadVendida = myStringCantidadVendida;
        this.myStringCondicion = myStringCondicion;
        this.myGeolocation = myGeolocation;
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringTitulo() {
        return myStringTitulo;
    }

    public void setMyStringTitulo(String myStringTitulo) {
        this.myStringTitulo = myStringTitulo;
    }

    public String getMyStringPrecio() {
        return myStringPrecio;
    }

    public void setMyStringPrecio(String myStringPrecio) {
        this.myStringPrecio = myStringPrecio;
    }

    public String getMyStringCantidadDisponible() {
        return myStringCantidadDisponible;
    }

    public void setMyStringCantidadDisponible(String myStringCantidadDisponible) {
        this.myStringCantidadDisponible = myStringCantidadDisponible;
    }

    public String getMyStringCantidadVendida() {
        return myStringCantidadVendida;
    }

    public void setMyStringCantidadVendida(String myStringCantidadVendida) {
        this.myStringCantidadVendida = myStringCantidadVendida;
    }

    public String getMyStringCondicion() {
        return myStringCondicion;
    }

    public void setMyStringCondicion(String myStringCondicion) {
        this.myStringCondicion = myStringCondicion;
    }

    public Geolocation getMyGeolocation() {
        return myGeolocation;
    }

    public void setMyGeolocation(Geolocation myGeolocation) {
        this.myGeolocation = myGeolocation;
    }
}
