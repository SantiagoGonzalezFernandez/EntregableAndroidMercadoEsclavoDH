package com.santiagogonzalez.mercadoesclavodh.model.data;

import android.util.Log;

import com.santiagogonzalez.mercadoesclavodh.model.container.ContainerProducto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoRetrofitDao;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoDAO extends ProductoRetrofitDao {

    private static final String BASE_URL = "https://api.mercadolibre.com/";

    public ProductoDAO() {
        super(BASE_URL);
    }

    public void traerProductoPorBusqueda(final ResultListener<List<Producto>> myResultListener, String query){
        Call<ContainerProducto> myCall = myService.traerProductoPorBusqueda(query);

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
