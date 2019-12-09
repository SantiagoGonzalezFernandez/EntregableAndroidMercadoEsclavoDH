package com.santiagogonzalez.mercadoesclavodh.model;

import com.santiagogonzalez.mercadoesclavodh.util.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoRetrofitDao {

    private Retrofit myRetrofit;
    protected Service myService;

    public ProductoRetrofitDao(String baseURL) {
        myRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myService = myRetrofit.create(Service.class);
    }
}
