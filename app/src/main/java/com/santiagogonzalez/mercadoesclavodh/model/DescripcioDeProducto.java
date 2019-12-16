package com.santiagogonzalez.mercadoesclavodh.model;

import java.io.Serializable;

public class DescripcioDeProducto implements Serializable {

    private String id;
    private String plain_text;

    public DescripcioDeProducto() {
    }

    public DescripcioDeProducto(String id, String plain_text) {
        this.id = id;
        this.plain_text = plain_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlain_text() {
        return plain_text;
    }

    public void setPlain_text(String plain_text) {
        this.plain_text = plain_text;
    }
}
