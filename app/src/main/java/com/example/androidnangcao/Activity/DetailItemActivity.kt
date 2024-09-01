package com.example.androidnangcao.Activity

import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityDetailItemBinding
import com.example.androidnangcao.model.Item

class DetailItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            finish()
        }
        val item = intent.getSerializableExtra("Item") as? Item

        // Cập nhật các TextView và EditText với thông tin từ item
        item?.let {
            findViewById<TextView>(R.id.tvNameItem).setText(it.name)
            findViewById<TextView>(R.id.tvPrice).text = String.format("%s VND", it.price.toString())
            findViewById<TextView>(R.id.tvPrice).paintFlags = findViewById<TextView>(R.id.tvPrice).paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            val priceDiscount = it.price.toDouble() - it.price.toDouble() * it.discountPercent.toDouble() / 100
            findViewById<TextView>(R.id.tvPriceDiscount).text = String.format("%d VND", priceDiscount.toInt())
            findViewById<TextView>(R.id.tvDiscountPercent).text = String.format("- %s %%", it.discountPercent.toString())
            findViewById<TextView>(R.id.tvDescription).setText(it.description.toString())
        }
    }
}