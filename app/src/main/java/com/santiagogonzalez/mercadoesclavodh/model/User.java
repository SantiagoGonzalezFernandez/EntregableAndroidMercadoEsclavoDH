package com.santiagogonzalez.mercadoesclavodh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("nombre")
    private String myStringNombre;

    @SerializedName("apellido")
    private String myStringApellido;

    @SerializedName("edad")
    private String myStringEdad;

    @SerializedName("email")
    private String myStringEmail;

    @SerializedName("nacionalidad")
    private String myStringNacionalidad;

    @SerializedName("password")
    private String myStringPassword;

    public User(String myStringNombre, String myStringApellido, String myStringEdad, String myStringEmail, String myStringNacionalidad, String myStringPassword) {
        this.myStringNombre = myStringNombre;
        this.myStringApellido = myStringApellido;
        this.myStringEdad = myStringEdad;
        this.myStringEmail = myStringEmail;
        this.myStringNacionalidad = myStringNacionalidad;
        this.myStringPassword = myStringPassword;
    }

    public User() {
    }

    public String getMyStringNombre() {
        return myStringNombre;
    }

    public void setMyStringNombre(String myStringNombre) {
        this.myStringNombre = myStringNombre;
    }

    public String getMyStringApellido() {
        return myStringApellido;
    }

    public void setMyStringApellido(String myStringApellido) {
        this.myStringApellido = myStringApellido;
    }

    public String getMyStringEdad() {
        return myStringEdad;
    }

    public void setMyStringEdad(String myStringEdad) {
        this.myStringEdad = myStringEdad;
    }

    public String getMyStringEmail() {
        return myStringEmail;
    }

    public void setMyStringEmail(String myStringEmail) {
        this.myStringEmail = myStringEmail;
    }

    public String getMyStringNacionalidad() {
        return myStringNacionalidad;
    }

    public void setMyStringNacionalidad(String myStringNacionalidad) {
        this.myStringNacionalidad = myStringNacionalidad;
    }

    public String getMyStringPassword() {
        return myStringPassword;
    }

    public void setMyStringPassword(String myStringPassword) {
        this.myStringPassword = myStringPassword;
    }
}
