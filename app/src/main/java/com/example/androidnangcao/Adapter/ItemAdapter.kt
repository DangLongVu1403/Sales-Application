package com.example.androidnangcao.Adapter
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnangcao.Activity.AddItemActivity
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.R
import com.example.androidnangcao.model.Item
import com.squareup.picasso.Picasso

class ItemAdapter(
    private var itemList: MutableList<Item>,
    private val rvInterfaceItem: RecyclerViewOnClick
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            val imgItem = findViewById<ImageView>(R.id.imgItem)
            val tvNameItem = findViewById<TextView>(R.id.tvNameItem)
            val tvPrice = findViewById<TextView>(R.id.tvPrice)
            val tvPriceDiscount = findViewById<TextView>(R.id.tvPriceDiscount)
            val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
            val btnUpdate = findViewById<ImageView>(R.id.btnEditItem)
            val btnDelete = findViewById<ImageView>(R.id.btnDeleteItem)

            val item = itemList[position]
            tvNameItem.text = item.name
            tvPrice.text = String.format("%d VND", item.price.toInt())
            val priceDiscount= item.price.toDouble() - item.price.toDouble() * item.discountPercent.toDouble()/100
            tvPriceDiscount.text = String.format("%d VND", priceDiscount.toInt())
            tvQuantity.text = item.quantity.toString()

            tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            if (item.urlImg.isNotEmpty()) {
                Picasso.get().load(item.urlImg).into(imgItem)
            } else {
                // Tải một hình ảnh mặc định nếu URL rỗng
                Picasso.get().load(R.drawable.ic_item).into(imgItem)
            }

            btnUpdate.setOnClickListener {
                val intent = Intent(context,AddItemActivity::class.java)
                intent.putExtra("Item",item)
                context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                // Tạo một AlertDialog
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Xác nhận xóa")
                builder.setMessage("Bạn có chắc muốn xóa item này không?")

                // Nút "Xóa"
                builder.setPositiveButton("Xóa") { dialog, which ->
                    // Thực hiện hành động xóa item
                    removeItem(position)
                }

                // Nút "Hủy"
                builder.setNegativeButton("Hủy") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
            holder.itemView.setOnClickListener {
                rvInterfaceItem.onClickItem(position)
            }
        }
    }

    // Hàm để xóa item khỏi danh sách và cập nhật adapter
    private fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemList.size)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
