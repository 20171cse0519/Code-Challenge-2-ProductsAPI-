package com.example.productsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productsapi.adapter.ProductListAdapter
import com.example.productsapi.data.ProductModel
import com.example.productsapi.databinding.ActivityMainBinding
import com.example.productsapi.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView(){
        binding.productListRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = ProductListAdapter(this)
        binding.productListRecyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel(){
        val viewModel: MainActivityViewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it!=null){
                recyclerAdapter.setProductList(it)
                recyclerAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"Error in getting list",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}