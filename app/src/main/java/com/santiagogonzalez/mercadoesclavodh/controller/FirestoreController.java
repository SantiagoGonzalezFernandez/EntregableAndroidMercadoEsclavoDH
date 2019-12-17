package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.FirestoreDao;
import com.santiagogonzalez.mercadoesclavodh.model.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class FirestoreController {

    private FirestoreDao firestoreDao;

    public FirestoreController() {
        firestoreDao = new FirestoreDao();
    }

    public void agregarProductoAFav(Producto myProducto){
        firestoreDao.agregarProductoAFav(myProducto);
    }

    //traigo las peliculas y se las paso a la vista
    public void traerListaDeFavorito(final ResultListener<List<Producto>> listenerDeLaVista){
        firestoreDao.traerProductosFavoritos(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }
}
