package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;


public class Usuario {

    private String myStringNombre;

    private String myStringApellido;

    private String myStringEdad;

    private String myStringEmail;

    private String myStringNacionalidad;

    private String myStringPassword;

    public Usuario() {
    }

    public Usuario(String myStringNombre, String myStringApellido, String myStringEdad, String myStringEmail, String myStringNacionalidad, String myStringPassword) {
        this.myStringNombre = myStringNombre;
        this.myStringApellido = myStringApellido;
        this.myStringEdad = myStringEdad;
        this.myStringEmail = myStringEmail;
        this.myStringNacionalidad = myStringNacionalidad;
        this.myStringPassword = myStringPassword;
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
