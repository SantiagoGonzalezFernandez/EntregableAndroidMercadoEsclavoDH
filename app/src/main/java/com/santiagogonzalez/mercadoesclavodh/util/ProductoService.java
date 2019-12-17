package com.santiagogonzalez.mercadoesclavodh.util;

import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("sites/MLA/search")
    Call<ProductoContainer> getCallProductosPorBusqueda(@Query("q") String myStringQuery,
                                           @Query("limit") Integer myIntegerLimit);

    @GET("sites/MLA/search")
    Call<ProductoContainer> getCallAgregarProductos(@Query("q") String myStringQuery,
                                            @Query("offset") Integer myIntegerOffset,
                                            @Query("limit") Integer myIntegerLimit);

    @GET("items/{id}/descriptions")
    Call<List<DescripcioDeProducto>> getCallDescripcionProducto(@Path("id") String myStringIdProducto);

    @GET("items/{id}")
    Call<CaracteristicasDelProducto> getCallCaracteristicasDelProducto(@Path("id") String myStringIdProducto);
}
