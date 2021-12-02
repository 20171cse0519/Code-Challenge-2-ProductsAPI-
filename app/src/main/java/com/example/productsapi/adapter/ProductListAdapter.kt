package com.example.productsapi.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productsapi.R
import com.example.productsapi.data.ProductModel
import com.example.productsapi.databinding.ProductListRowBinding

class ProductListAdapter(val activity: Activity) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    private var productList: List<ProductModel>? = null

    fun setProductList(productList: List<ProductModel>?){
        this.productList = productList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_row, parent, false)

        return MyViewHolder(ProductListRowBinding.bind(view))


    }

    override fun onBindViewHolder(holder: ProductListAdapter.MyViewHolder, position: Int) {
        productList?.let { holder.bind(it.get(position),activity) }
    }

    override fun getItemCount(): Int {
        if(productList == null)return 0
        else return productList?.size!!
    }

    class MyViewHolder(private val itemBinding: ProductListRowBinding): RecyclerView.ViewHolder(itemBinding.root){
        val productImage = itemBinding.productImage
        val tvTitle = itemBinding.tvTitle
        val tvPrice = itemBinding.tvPrice
        val tvDescription = itemBinding.tvDescription
        val tvCategory = itemBinding.tvCategory

        fun bind(data: ProductModel, activity: Activity){
            tvTitle.text = data.title
            tvPrice.text = "price: "+data.price.toString() + "$"
            tvDescription.text = data.description
            tvCategory.text = "category: "+ data.category


            Glide.with(itemBinding.root).load(data.image).into(itemBinding.productImage)







        }
    }
}