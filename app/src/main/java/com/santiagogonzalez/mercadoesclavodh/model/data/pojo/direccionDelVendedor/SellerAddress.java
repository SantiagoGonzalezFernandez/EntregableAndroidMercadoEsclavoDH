package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor;

import com.google.gson.annotations.SerializedName;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor.searchLocation.SearchLocation;

import java.io.Serializable;

public class SellerAddress implements Serializable {

    @SerializedName("city")
    private City myCity;

    @SerializedName("state")
    private State myState;

    @SerializedName("country")
    private Country myCountry;

    @SerializedName("search_location")
    private SearchLocation mySearchLocation;

    @SerializedName("latitude")
    private Float myFloatLatitud;

    @SerializedName("longitude")
    private Float myFloatLongitud;

    @SerializedName("id")
    private int myIntId;

    public SellerAddress() {
    }

    public SellerAddress(City myCity, State myState, Country myCountry, SearchLocation mySearchLocation, Float myFloatLatitud, Float myFloatLongitud, int myIntId) {
        this.myCity = myCity;
        this.myState = myState;
        this.myCountry = myCountry;
        this.mySearchLocation = mySearchLocation;
        this.myFloatLatitud = myFloatLatitud;
        this.myFloatLongitud = myFloatLongitud;
        this.myIntId = myIntId;
    }

    public City getMyCity() {
        return myCity;
    }

    public void setMyCity(City myCity) {
        this.myCity = myCity;
    }

    public State getMyState() {
        return myState;
    }

    public void setMyState(State myState) {
        this.myState = myState;
    }

    public Country getMyCountry() {
        return myCountry;
    }

    public void setMyCountry(Country myCountry) {
        this.myCountry = myCountry;
    }

    public SearchLocation getMySearchLocation() {
        return mySearchLocation;
    }

    public void setMySearchLocation(SearchLocation mySearchLocation) {
        this.mySearchLocation = mySearchLocation;
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

    public int getMyIntId() {
        return myIntId;
    }

    public void setMyIntId(int myIntId) {
        this.myIntId = myIntId;
    }
}
