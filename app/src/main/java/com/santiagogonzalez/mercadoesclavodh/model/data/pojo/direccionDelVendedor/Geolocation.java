package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geolocation implements Serializable {

    @SerializedName("latitude")
    private Float myFloatLatitud;

    @SerializedName("longitude")
    private Float myFloatLongitud;

    public Geolocation(Float myFloatLatitud, Float myFloatLongitud) {
        this.myFloatLatitud = myFloatLatitud;
        this.myFloatLongitud = myFloatLongitud;
    }

    public Geolocation() {
    }

    public Float getMyFloatLatitud() {
        return myFloatLatitud;
    }

    public void setMyFloatLatitud(Float myFloatLatitud) {
        this.myFloatLatitud = myFloatLatitud;
    }

    public Float getMyFloatLongitud() {
        return myFloatLongitud;
    }

    public void setMyFloatLongitud(Float myFloatLongitud) {
        this.myFloatLongitud = myFloatLongitud;
    }
}
