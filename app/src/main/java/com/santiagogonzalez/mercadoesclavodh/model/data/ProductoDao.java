package com.santiagogonzalez.mercadoesclavodh.model.data;

import android.util.Log;

import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
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
    public static final String BASE_URL = "https://api.mercadolibre.com/";

    public static final String PRODUCTO_SELECCIONADO = "producto_seleccionado";

    public ProductoDao() {
        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myProductoService = myRetrofit.create(ProductoService.class);
    }

    public void obtenerResultadoDao(String query, final ResultListener<ProductoContainer> myEscuchadorDelControler, Integer offset, Integer total){
        Call<ProductoContainer> myCallProductos = myProductoService.getCallAgregarMasProductos(query,offset,total);

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
        Call<ProductoContainer> myCall = myProductoService.getCallService(movieBusqueda,0);

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

    public void traerDescripcionPorId(final ResultListener<DescripcioDeProducto> resultListener, String idProducto){
        Call<List<DescripcioDeProducto>> myCall = myProductoService.getDescripcionProducto(idProducto);

        myCall.enqueue(new Callback<List<DescripcioDeProducto>>() {
            @Override
            public void onResponse(Call<List<DescripcioDeProducto>> call, Response<List<DescripcioDeProducto>> response) {
                DescripcioDeProducto myDescripcioDeProducto = response.body().get(0);
                resultListener.finish(myDescripcioDeProducto);
            }

            @Override
            public void onFailure(Call<List<DescripcioDeProducto>> call, Throwable t) {
                Log.d("Error","En el traer descripcion de producto por id");
            }
        });
    }

    public void traerCaracteristicasPorId(final ResultListener<CaracteristicasDelProducto> resultListener, String idProducto){
        Call<CaracteristicasDelProducto> myCall = myProductoService.getCaracteristicasDelProducto(idProducto);

        myCall.enqueue(new Callback<CaracteristicasDelProducto>() {
            @Override
            public void onResponse(Call<CaracteristicasDelProducto> call, Response<CaracteristicasDelProducto> response) {
                CaracteristicasDelProducto myCaracteristicasDelProducto = response.body();
                resultListener.finish(myCaracteristicasDelProducto);
            }
            @Override
            public void onFailure(Call<CaracteristicasDelProducto> call, Throwable t) {
                Log.d("Error","En el traer CaracteristicasDelProducto por id");
            }
        });
    }


}
