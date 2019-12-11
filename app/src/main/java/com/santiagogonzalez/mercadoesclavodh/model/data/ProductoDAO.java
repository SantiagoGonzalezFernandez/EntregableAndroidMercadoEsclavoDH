package com.santiagogonzalez.mercadoesclavodh.model.data;

import android.util.Log;

import com.santiagogonzalez.mercadoesclavodh.model.container.ContainerProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.util.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoDAO{

    private Service myService;
    private Retrofit myRetrofit;
    public static final String BASE_URL = "https://api.mercadolibre.com";///sites/MLA/
    public static final String PRODUCTO_SELECCIONADO = "producto";

    public ProductoDAO() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myService = retrofit.create(Service.class);
    }

    public void traerProductoPorBusqueda(String PRODUCTO_SELECCIONADO,final ResultListener<List<Producto>> myResultListener){
        Call<ContainerProducto> myCall = myService.traerProductoPorBusqueda(PRODUCTO_SELECCIONADO);

        myCall.enqueue(new Callback<ContainerProducto>() {
            @Override
            public void onResponse(Call<ContainerProducto> myContainerProductoCall, Response<ContainerProducto> myResponse) {
                ContainerProducto myContainerProducto = myResponse.body();
                myResultListener.finish(myContainerProducto.getResults());
            }

            @Override
            public void onFailure(Call<ContainerProducto> call, Throwable t) {
                Log.d("Error","Fallo en el ProductoDao");
            }
        });
    }
}
