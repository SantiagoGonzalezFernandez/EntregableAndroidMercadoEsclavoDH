package com.santiagogonzalez.mercadoesclavodh.controller;

import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.ProductoDao;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class ProductoController {

    private ProductoDao myProductoDao;

    public static final Integer LIMIT = 10;
    private Integer myIntegerTotal;
    private Integer myIntegerOffset = 0;
    private Boolean myBooleanTrajoMasPost = true; 
    private  Boolean myBooleanHayUnPedidoEnCurso = false;

    public ProductoController() {
        this.myProductoDao = new ProductoDao();
    }

    public void obtenerResultadoController(final Integer myIntegerSizeDeLaLista, String myStringQuery, final ResultListener<ProductoContainer> myEscuchadorDeLaVista){
        if(!myBooleanHayUnPedidoEnCurso){
            myBooleanHayUnPedidoEnCurso = true;
            myProductoDao.obtenerResultadoDao(myStringQuery,new ResultListener<ProductoContainer>() {
                @Override
                public void finish(ProductoContainer results) {
                    myIntegerTotal = results.getPaging().getTotal();
                    myBooleanHayUnPedidoEnCurso = false;
                    myIntegerOffset = myIntegerOffset + LIMIT;
                    myEscuchadorDeLaVista.finish(results);
                    if(myIntegerSizeDeLaLista + myIntegerOffset >= myIntegerTotal){
                        myBooleanTrajoMasPost = false;
                    }
                }
            }, myIntegerOffset,LIMIT);
        }
    }

    public void traerProductoPorBusqueda(String myStringProductoBusqueda, final ResultListener<List<Producto>> myListenerDeLaVista){
        myProductoDao.traerProductoPorBusqueda(new ResultListener<List<Producto>>(){
            @Override
            public void finish(List<Producto> result) {
                myListenerDeLaVista.finish(result);
            }
        }, myStringProductoBusqueda);
    }

    public void traerDescripcionPorId(String myStringIdProducto, final ResultListener<DescripcioDeProducto> myListResultListener){
        myProductoDao.traerDescripcionPorId(new ResultListener<DescripcioDeProducto>() {
            @Override
            public void finish(DescripcioDeProducto results) {
                myListResultListener.finish(results);
            }
        }, myStringIdProducto);
    }

    public void traerCaracteristicasPorId(String idProducto, final ResultListener<CaracteristicasDelProducto> listResultListener){
        myProductoDao.traerCaracteristicasPorId(new ResultListener<CaracteristicasDelProducto>() {
            @Override
            public void finish(CaracteristicasDelProducto results) {
                listResultListener.finish(results);
            }
        }, idProducto);
    }

}

