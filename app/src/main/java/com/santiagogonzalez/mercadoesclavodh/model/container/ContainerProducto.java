package com.santiagogonzalez.mercadoesclavodh.model.container;

import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Paging;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.List;

public class ContainerProducto {

    private List<Producto> results;

    private Paging paging;

    public List<Producto> getResults() {
        return results;
    }
}
