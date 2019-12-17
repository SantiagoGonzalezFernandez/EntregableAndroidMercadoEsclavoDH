package com.santiagogonzalez.mercadoesclavodh.model;

import java.io.Serializable;

public class Geolocation implements Serializable {

    private Double latitude;
    private Double longitude;

    public Geolocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geolocation() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
