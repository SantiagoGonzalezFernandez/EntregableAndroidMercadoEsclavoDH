package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.Producto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoDao;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class ProductoController {

    private ProductoDao productoDao;

    public ProductoController() {
        this.productoDao = new ProductoDao();
    }

    public void obtenerResultadoController(String productoSeleccionado,final ResultListener<ProductoContainer> escuchadorDeLaVista){
        productoDao.obtenerResultadoDao(productoSeleccionado,new ResultListener<ProductoContainer>() {
            @Override
            public void finish(ProductoContainer results) {
                escuchadorDeLaVista.finish(results);
            }
        });
    }

    public void traerProductoPorBusqueda(String movieBusqueda, final ResultListener<List<Producto>> listenerDeLaVista){
        productoDao.traerProductoPorBusqueda(new ResultListener<List<Producto>>(){
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        }, movieBusqueda);
    }

}

