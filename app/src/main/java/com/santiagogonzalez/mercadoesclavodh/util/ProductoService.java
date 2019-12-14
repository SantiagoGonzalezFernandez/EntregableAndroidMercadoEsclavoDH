package com.santiagogonzalez.mercadoesclavodh.util;

import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("search")
    Call<ProductoContainer> getCallService(@Query("q") String productoBuscado);

}
