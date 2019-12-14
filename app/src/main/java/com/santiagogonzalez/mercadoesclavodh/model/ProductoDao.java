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


    private ProductoService productoService;
    private Retrofit retrofit;
    public static final String BASE_URL = "https://api.mercadolibre.com/sites/MLA/";

    public static final String PRODUCTO_SELECCIONADO = "producto_seleccionado";

    public ProductoDao() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productoService = retrofit.create(ProductoService.class);
    }

    public void obtenerResultadoDao(String PRODUCTO_SELECCIONADO,final ResultListener<ProductoContainer> escuchadorDelControler){
        Call<ProductoContainer> callProductos = productoService.getCallService(PRODUCTO_SELECCIONADO);

        callProductos.enqueue(new Callback<ProductoContainer>() {
            @Override
            public void onResponse(Call<ProductoContainer> call, Response<ProductoContainer> response) {
                ProductoContainer productoContainerDeInternet = response.body();
                escuchadorDelControler.finish(productoContainerDeInternet);
            }

            @Override
            public void onFailure(Call<ProductoContainer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void traerProductoPorBusqueda(final ResultListener<List<Producto>> resultListenerController, String movieBusqueda){
        Call<ProductoContainer> call = productoService.getCallService(movieBusqueda);

        call.enqueue(new Callback<ProductoContainer>() {
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
