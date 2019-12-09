package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Paging implements Serializable {

    @SerializedName("total")
    private String myStringTotalDeResultados;

    @SerializedName("offset")
    private String myStringDeQuePagina;

    @SerializedName("limit")
    private String myStringHastaQuePagina;

    @SerializedName("primary_results")
    private String myStringResultadosPrimarios;

    public Paging(String myStringTotalDeResultados, String myStringDeQuePagina, String myStringHastaQuePagina, String myStringResultadosPrimarios) {
        this.myStringTotalDeResultados = myStringTotalDeResultados;
        this.myStringDeQuePagina = myStringDeQuePagina;
        this.myStringHastaQuePagina = myStringHastaQuePagina;
        this.myStringResultadosPrimarios = myStringResultadosPrimarios;
    }

    public Paging() {
    }

    public String getMyStringTotalDeResultados() {
        return myStringTotalDeResultados;
    }

    public void setMyStringTotalDeResultados(String myStringTotalDeResultados) {
        this.myStringTotalDeResultados = myStringTotalDeResultados;
    }

    public String getMyStringDeQuePagina() {
        return myStringDeQuePagina;
    }

    public void setMyStringDeQuePagina(String myStringDeQuePagina) {
        this.myStringDeQuePagina = myStringDeQuePagina;
    }

    public String getMyStringHastaQuePagina() {
        return myStringHastaQuePagina;
    }

    public void setMyStringHastaQuePagina(String myStringHastaQuePagina) {
        this.myStringHastaQuePagina = myStringHastaQuePagina;
    }

    public String getMyStringResultadosPrimarios() {
        return myStringResultadosPrimarios;
    }

    public void setMyStringResultadosPrimarios(String myStringResultadosPrimarios) {
        this.myStringResultadosPrimarios = myStringResultadosPrimarios;
    }
}
