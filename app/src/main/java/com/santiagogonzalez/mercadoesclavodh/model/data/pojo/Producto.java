package com.santiagogonzalez.mercadoesclavodh.model;

import java.io.Serializable;

public class Producto implements Serializable {

    private String id;
    private String title;
    private String thumbnail;
    private String price;

    public Producto(String id, String title, String thumbnail, String price) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public Producto() {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
