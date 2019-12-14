package com.santiagogonzalez.mercadoesclavodh.model;

import java.util.List;

public class ProductoContainer {

    List<Producto> results;

    public ProductoContainer(List<Producto> results) {
        this.results = results;
    }

    public ProductoContainer() {
    }


    public List<Producto> getResults() {
        return results;
    }

    public void setResults(List<Producto> results) {
        this.results = results;
    }
}
