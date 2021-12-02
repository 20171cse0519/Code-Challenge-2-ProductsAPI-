package com.example.productsapi.retrofit

import com.example.productsapi.data.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("products")
    fun getProductList()  : Call<List<ProductModel>>
}