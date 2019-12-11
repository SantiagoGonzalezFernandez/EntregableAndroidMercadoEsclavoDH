package com.santiagogonzalez.mercadoesclavodh.model.container;

import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.List;

public class ContainerProducto {

    List<Producto> results;

    public ContainerProducto(List<Producto> results) {
        this.results = results;
    }

    public ContainerProducto() {
    }

    public List<Producto> getResults() {
        return results;
    }

    public void setResults(List<Producto> results) {
        this.results = results;
    }
}
