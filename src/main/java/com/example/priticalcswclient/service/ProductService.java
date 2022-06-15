package com.example.priticalcswclient.service;

import com.example.priticalcswclient.entity.Product;
import com.example.priticalcswclient.entity.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {
    @GET("/api/v1/products")
    public Call<List<Product>> getProducts();

    @GET("api/v1/products/{id}")
    public Call<Product> getProductDetails(@Path("id") Integer id);

    @POST("api/v1/products")
    public Call<Product> addProducts(@Body Product product);

    @PUT("api/v1/products/{id}")
    public Call<Boolean> updateProducts(@Path("id") Integer id, @Body Product Product);
}

