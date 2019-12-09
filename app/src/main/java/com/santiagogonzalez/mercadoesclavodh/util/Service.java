package com.santiagogonzalez.mercadoesclavodh.util;

import com.santiagogonzalez.mercadoesclavodh.model.container.ContainerProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Descriptions;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("sites/MLA/search")
    Call<ContainerProducto> traerProductoPorBusqueda(@Query("q") String stringQuery);

    @GET("items/{producto_id}")
    Call<Producto> traerProducto(@Path("producto_id") String stringProductoId);

    @GET("items/{producto_id}/descriptions")
    Call<List<Descriptions>> traerDescripcionProducto(@Path("producto_id") String stringProductoId);

}
