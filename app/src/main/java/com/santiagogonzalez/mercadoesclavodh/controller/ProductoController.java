package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.Producto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoDao;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class ProductoController {

    private ProductoDao productoDao;

    public static final Integer LIMIT = 10;
    private Integer total;
    private Integer offset = 0;
    private Boolean trajoMasPost = true;
    private  Boolean hayUnPedidoEnCurso = false;

    public ProductoController() {
        this.productoDao = new ProductoDao();
    }

    public void obtenerResultadoController(final Integer longituLista, String query, final ResultListener<ProductoContainer> escuchadorDeLaVista){
        if(!hayUnPedidoEnCurso){
            hayUnPedidoEnCurso = true;
            productoDao.obtenerResultadoDao(query,new ResultListener<ProductoContainer>() {
                @Override
                public void finish(ProductoContainer results) {
                    total = results.getPaging().getTotal();
                    hayUnPedidoEnCurso = false;
                    offset = offset + LIMIT;
                    escuchadorDeLaVista.finish(results);
                    if(longituLista + offset >= total){
                        trajoMasPost = false;
                    }
                }
            }, offset,LIMIT);
        }
    }

    public void traerProductoPorBusqueda(String productoBusqueda, final ResultListener<List<Producto>> listenerDeLaVista){
        productoDao.traerProductoPorBusqueda(new ResultListener<List<Producto>>(){
            @Override
            public void finish(List<Producto> result) {
                listenerDeLaVista.finish(result);
            }
        }, productoBusqueda);
    }

    public void traerDescripcionPorId(String idProducto, final ResultListener<DescripcioDeProducto> listResultListener){
        productoDao.traerDescripcionPorId(new ResultListener<DescripcioDeProducto>() {
            @Override
            public void finish(DescripcioDeProducto results) {
                listResultListener.finish(results);
            }
        }, idProducto);
    }

    public void traerCaracteristicasPorId(String idProducto, final ResultListener<CaracteristicasDelProducto> listResultListener){
        productoDao.traerCaracteristicasPorId(new ResultListener<CaracteristicasDelProducto>() {
            @Override
            public void finish(CaracteristicasDelProducto results) {
                listResultListener.finish(results);
            }
        }, idProducto);
    }

}

