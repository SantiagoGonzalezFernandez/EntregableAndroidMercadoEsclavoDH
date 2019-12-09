package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pictures implements Serializable {

    @SerializedName("id")
    private String myStringId;

    @SerializedName("url")
    private String myStringUrl;

    @SerializedName("secure_url")
    private String myStringUrlSegura;

    @SerializedName("size")
    private String myStringDimencion;

    @SerializedName("max_size")
    private String myStringMaximaDimencion;

    public Pictures() {
    }

    public Pictures(String myStringId, String myStringUrl, String myStringUrlSegura, String myStringDimencion, String myStringMaximaDimencion) {
        this.myStringId = myStringId;
        this.myStringUrl = myStringUrl;
        this.myStringUrlSegura = myStringUrlSegura;
        this.myStringDimencion = myStringDimencion;
        this.myStringMaximaDimencion = myStringMaximaDimencion;
    }

    public String getMyStringId() {
        return myStringId;
    }

    public void setMyStringId(String myStringId) {
        this.myStringId = myStringId;
    }

    public String getMyStringUrl() {
        return myStringUrl;
    }

    public void setMyStringUrl(String myStringUrl) {
        this.myStringUrl = myStringUrl;
    }

    public String getMyStringUrlSegura() {
        return myStringUrlSegura;
    }

    public void setMyStringUrlSegura(String myStringUrlSegura) {
        this.myStringUrlSegura = myStringUrlSegura;
    }

    public String getMyStringDimencion() {
        return myStringDimencion;
    }

    public void setMyStringDimencion(String myStringDimencion) {
        this.myStringDimencion = myStringDimencion;
    }

    public String getMyStringMaximaDimencion() {
        return myStringMaximaDimencion;
    }

    public void setMyStringMaximaDimencion(String myStringMaximaDimencion) {
        this.myStringMaximaDimencion = myStringMaximaDimencion;
    }
}
