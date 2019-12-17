package com.santiagogonzalez.mercadoesclavodh.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CaracteristicasDelProducto implements Serializable {

    private String id;

    private String title;

    private String price;

    private String available_quantity;

    private String sold_quantity;

    private String condition;

    @SerializedName("geolocation")
    private Geolocation myGeolocation;

    public CaracteristicasDelProducto(String id, String title, String price, String available_quantity, String sold_quantity, String condition, Geolocation myGeolocation) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.available_quantity = available_quantity;
        this.sold_quantity = sold_quantity;
        this.condition = condition;
        this.myGeolocation = myGeolocation;
    }

    public CaracteristicasDelProducto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(String available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(String sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Geolocation getMyGeolocation() {
        return myGeolocation;
    }

    public void setMyGeolocation(Geolocation myGeolocation) {
        this.myGeolocation = myGeolocation;
    }
}
