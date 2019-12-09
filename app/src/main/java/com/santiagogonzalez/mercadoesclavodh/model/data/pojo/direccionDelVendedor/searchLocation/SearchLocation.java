package com.santiagogonzalez.mercadoesclavodh.model.data.pojo.direccionDelVendedor.searchLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchLocation implements Serializable {

    @SerializedName("neighborhood")
    private Neighborhood myNeighborhood;

    @SerializedName("city")
    private City myCity;

    @SerializedName("state")
    private State myState;

    public SearchLocation() {
    }

    public SearchLocation(Neighborhood myNeighborhood, City myCity, State myState) {
        this.myNeighborhood = myNeighborhood;
        this.myCity = myCity;
        this.myState = myState;
    }

    public Neighborhood getMyNeighborhood() {
        return myNeighborhood;
    }

    public void setMyNeighborhood(Neighborhood myNeighborhood) {
        this.myNeighborhood = myNeighborhood;
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
}
