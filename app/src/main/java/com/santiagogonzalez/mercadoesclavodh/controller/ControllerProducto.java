package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.model.data.ProductoDAO;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class ControllerProducto {

    private ProductoDAO myProductoDAO;

    public ControllerProducto() {
        this.myProductoDAO = new ProductoDAO();
    }

    public void traerProductoPorBusqueda(String query, final ResultListener<List<Producto>> myResultListener){
        myProductoDAO.traerProductoPorBusqueda(new ResultListener<List<Producto>>(){
            @Override
            public void finish(List<Producto> result) {
                myResultListener.finish(result);
            }
        }, query);
    }

}
