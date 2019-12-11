package com.santiagogonzalez.mercadoesclavodh.util;

import com.santiagogonzalez.mercadoesclavodh.model.container.ContainerProducto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("sites/MLA/search")
    Call<ContainerProducto> traerProductoPorBusqueda(@Query("q") String stringQuery);

}
