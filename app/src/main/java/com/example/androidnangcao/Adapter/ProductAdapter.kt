package com.example.androidnangcao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnangcao.R
import com.example.androidnangcao.model.Item
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList: List<Item>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
        private val productImage: ImageView = itemView.findViewById(R.id.product_image)
        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private val productPrice: TextView = itemView.findViewById(R.id.product_price)
        private val productSold: TextView = itemView.findViewById(R.id.product_sold)

        fun bind(product: Item) {
            if (product.urlImg.isNotEmpty()) {
                Picasso.get().load(product.urlImg).into(productImage)
            } else {
                Picasso.get().load(R.drawable.ic_item).into(productImage)
            }
            productName.text = product.name
            productPrice.text = "Giá: ${product.price} VND"
            productSold.text = "Đã bán: ${product.sold}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProducts(newProductList: List<Item>) {
        productList = newProductList
        notifyDataSetChanged()
    }
}
