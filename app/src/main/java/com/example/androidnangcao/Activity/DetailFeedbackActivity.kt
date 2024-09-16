package com.example.androidnangcao.Activity

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityDetailFeedbackBinding
import com.example.androidnangcao.model.Feedback
import com.example.androidnangcao.model.Item

class DetailFeedbackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }
        val feedback = intent.getSerializableExtra("Feedback") as? Feedback

        // Cập nhật các TextView và EditText với thông tin từ item
        feedback?.let {
            findViewById<TextView>(R.id.tvAddressEmail).setText(it.email)
            findViewById<TextView>(R.id.tvFeedback).setText(it.feedback)
        }
    }
}