package com.example.androidnangcao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.R
import com.example.androidnangcao.model.Order
import com.squareup.picasso.Picasso

class OrderAdapter(private var orderList: MutableList<Order>,
                   private val rvInterfaceItem: RecyclerViewOnClick
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(orderView: View) : RecyclerView.ViewHolder(orderView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.itemView.apply {
            val imgItem = findViewById<ImageView>(R.id.imgItemOrder)
            val tvNameItem = findViewById<TextView>(R.id.tvNameItemOrder)
            val tvQuantity = findViewById<TextView>(R.id.tvQuantityOrder)
            val tvTotal = findViewById<TextView>(R.id.tvTotal)

            val order = orderList[position]
            tvNameItem.text = order.nameItem
            tvQuantity.text = order.quantity.toString()
            tvTotal.text = order.total

            if (order.urlImg.isNotEmpty()) {
                Picasso.get().load(order.urlImg).into(imgItem)
            } else {
                // Tải một hình ảnh mặc định nếu URL rỗng
                Picasso.get().load(R.drawable.ic_item).into(imgItem)
            }


            holder.itemView.setOnClickListener {
                rvInterfaceItem.onClickItem(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}