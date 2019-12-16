package com.santiagogonzalez.mercadoesclavodh.model;

import android.util.Log;

import com.santiagogonzalez.mercadoesclavodh.util.ProductoService;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoDao {


    private ProductoService myProductoService;
    private Retrofit myRetrofit;
    public static final String BASE_URL = "https://api.mercadolibre.com/sites/MLA/";

    public static final String PRODUCTO_SELECCIONADO = "producto_seleccionado";

    public ProductoDao() {
        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myProductoService = myRetrofit.create(ProductoService.class);
    }

    public void obtenerResultadoDao(String PRODUCTO_SELECCIONADO,final ResultListener<ProductoContainer> myEscuchadorDelControler){
        Call<ProductoContainer> myCallProductos = myProductoService.getCallService(PRODUCTO_SELECCIONADO);

        myCallProductos.enqueue(new Callback<ProductoContainer>() {
            @Override
            public void onResponse(Call<ProductoContainer> call, Response<ProductoContainer> response) {
                ProductoContainer myProductoContainer = response.body();
                myEscuchadorDelControler.finish(myProductoContainer);
            }

            @Override
            public void onFailure(Call<ProductoContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void traerProductoPorBusqueda(final ResultListener<List<Producto>> resultListenerController, String movieBusqueda){
        Call<ProductoContainer> myCall = myProductoService.getCallService(movieBusqueda);

        myCall.enqueue(new Callback<ProductoContainer>() {
            @Override
            public void onResponse(Call<ProductoContainer> call, Response<ProductoContainer> response) {
                ProductoContainer myProductoContainer = response.body();
                resultListenerController.finish(myProductoContainer.getResults());
            }

            @Override
            public void onFailure(Call<ProductoContainer> call, Throwable t) {
                Log.d("Error","En el traer producto por busqueda");
            }
        });
    }
}
