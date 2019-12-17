package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geolocation implements Serializable {

    @SerializedName("latitude")
    private Double myDoubleLatitud;

    @SerializedName("longitude")
    private Double myDoubleLongitud;

    public Geolocation() {
    }

    public Geolocation(Double myDoubleLatitud, Double myDoubleLongitud) {
        this.myDoubleLatitud = myDoubleLatitud;
        this.myDoubleLongitud = myDoubleLongitud;
    }

    public Double getMyDoubleLatitud() {
        return myDoubleLatitud;
    }

    public void setMyDoubleLatitud(Double myDoubleLatitud) {
        this.myDoubleLatitud = myDoubleLatitud;
    }

    public Double getMyDoubleLongitud() {
        return myDoubleLongitud;
    }

    public void setMyDoubleLongitud(Double myDoubleLongitud) {
        this.myDoubleLongitud = myDoubleLongitud;
    }
}
