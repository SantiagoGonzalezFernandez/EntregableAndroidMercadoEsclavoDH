package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.data.FirestoreDao;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class FirestoreController {

    private FirestoreDao myFirestoreDao;

    public FirestoreController() {
        myFirestoreDao = new FirestoreDao();
    }

    public void agregarProductoAFav(Producto myProducto){
        myFirestoreDao.agregarProductoAFav(myProducto);
    }

    //traigo los productos y se las paso a la vista
    public void traerListaDeFavorito(final ResultListener<List<Producto>> myListenerDeLaVista){
        myFirestoreDao.traerProductosFavoritos(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> myResult) {
                myListenerDeLaVista.finish(myResult);
            }
        });
    }
}
