package com.santiagogonzalez.mercadoesclavodh.util;

import com.santiagogonzalez.mercadoesclavodh.model.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("sites/MLA/search")
    Call<ProductoContainer> getCallService(@Query("q") String productoBuscado);

    @GET("items/{id}/descriptions")
    Call<List<DescripcioDeProducto>> getDescripcionProducto(@Path("id") String idProducto);
}
