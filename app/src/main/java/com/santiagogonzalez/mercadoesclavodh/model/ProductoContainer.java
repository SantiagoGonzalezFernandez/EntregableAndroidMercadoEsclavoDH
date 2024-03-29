package com.santiagogonzalez.mercadoesclavodh.model;

import com.google.gson.annotations.SerializedName;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Paging;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductoContainer implements Serializable {

    @SerializedName("results")
    List<Producto> myProductoListResultado;

    @SerializedName("paging")
    private Paging myPaging;

    public ProductoContainer(List<Producto> myProductoListResultado) {
        this.myProductoListResultado = myProductoListResultado;
    }

    public ProductoContainer() {
        myProductoListResultado = new ArrayList<>();
    }

    public List<Producto> getMyProductoListResultado() {
        return myProductoListResultado;
    }

    public void setMyProductoListResultado(List<Producto> myProductoListResultado) {
        this.myProductoListResultado = myProductoListResultado;
    }

    public Paging getMyPaging() {
        return myPaging;
    }

    public void setMyPaging(Paging myPaging) {
        this.myPaging = myPaging;
    }


    public void agregarProducto(Producto myProducto){
        myProductoListResultado.add(myProducto);
    }
    public void removerProducto(Producto myProducto){
        myProductoListResultado.remove(myProducto);
    }

    public Boolean contieneElProducto(Producto myProducto){
        return myProductoListResultado.contains(myProducto);
    }
}
