package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Producto implements Serializable {

    @SerializedName("title")
    private String myStringTitulo;

    @SerializedName("thumbnail")
    private String myStringImagen;

    @SerializedName("price")
    private String myStringPrecio;

    public Producto(String myStringTitulo, String myStringImagen, String myStringPrecio) {
        this.myStringTitulo = myStringTitulo;
        this.myStringImagen = myStringImagen;
        this.myStringPrecio = myStringPrecio;
    }

    public Producto() {
    }

    public String getMyStringTitulo() {
        return myStringTitulo;
    }

    public void setMyStringTitulo(String myStringTitulo) {
        this.myStringTitulo = myStringTitulo;
    }

    public String getMyStringImagen() {
        return myStringImagen;
    }

    public void setMyStringImagen(String myStringImagen) {
        this.myStringImagen = myStringImagen;
    }

    public String getMyStringPrecio() {
        return myStringPrecio;
    }

    public void setMyStringPrecio(String myStringPrecio) {
        this.myStringPrecio = myStringPrecio;
    }
}
