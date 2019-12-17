package com.santiagogonzalez.mercadoesclavodh.model;

import java.util.List;

public class ProductoContainer {

    List<Producto> results;

    private Paging paging;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

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

    public void agregarProducto(Producto myProducto){
        results.add(myProducto);
    }
    public void removerProducto(Producto myProducto){
        results.remove(myProducto);
    }

    public Boolean contieneElProducto(Producto myProducto){
        return results.contains(myProducto);
    }
}
