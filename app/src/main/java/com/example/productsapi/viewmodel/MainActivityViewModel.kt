package com.example.productsapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productsapi.data.ProductModel
import com.example.productsapi.retrofit.RetroInstance
import com.example.productsapi.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {


    lateinit var liveDataList: MutableLiveData<List<ProductModel>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver() : MutableLiveData<List<ProductModel>>{
        return liveDataList
    }
     fun makeAPICall(){
         val retroInstance = RetroInstance.getRetroInstance()
         val retroService = retroInstance.create(RetroServiceInterface::class.java)
         val call = retroService.getProductList()
         call.enqueue(object: Callback<List<ProductModel>> {
             override fun onResponse(
                 call: Call<List<ProductModel>>,
                 response: Response<List<ProductModel>>
             ) {
                 liveDataList.postValue(response.body())
             }

             override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                 liveDataList.postValue(null)
             }
         })
     }
}