package com.example.androidnangcao.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidnangcao.Activity.ui_sales.home.HomeFragment
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityAddItemBinding
import com.example.androidnangcao.model.Item
import java.io.Serializable

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("Item")) {
            // Nhận dữ liệu Item từ Intent và ép kiểu về Item
            val item = intent.getSerializableExtra("Item") as? Item

            // Cập nhật các TextView và EditText với thông tin từ item
            item?.let {
                findViewById<TextView>(R.id.textView).text = getString(R.string.edit_product)
                findViewById<Button>(R.id.btnAddProduct).text = getString(R.string.edit)
                findViewById<EditText>(R.id.edtNameItem).setText(it.name)
                findViewById<EditText>(R.id.edtPrice).setText(it.price.toString())
                findViewById<EditText>(R.id.edtDiscount).setText(it.discountPercent.toString())
                findViewById<EditText>(R.id.edtDescription).setText(it.description.toString())
                findViewById<EditText>(R.id.edtUrlImg).setText(it.urlImg.toString())
                findViewById<EditText>(R.id.edtUrlImgBanner).setText(it.urlImgBanner.toString())
                // Cập nhật thêm các view khác tương ứng nếu cần
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnAddProduct.setOnClickListener {
            //add product
        }
    }
}