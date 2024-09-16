package com.example.androidnangcao.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnangcao.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sử dụng Handler để trì hoãn việc chuyển đổi Activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Chuyển sang Activity khác sau 5 giây
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Để đóng MainActivity sau khi chuyển đổi
        }, 1000) // 5000 milliseconds = 5 seconds

//        val notificationHelper = NotificationHelper(this)
//
//// Intent để mở Activity khi người dùng nhấn vào thông báo
//        val intent = Intent(this, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//// Gửi thông báo
//        notificationHelper.sendNotification(
//            notificationId = 1,
//            title = "Thông báo mới",
//            message = "Nội dung thông báo của bạn ở đây.",
//            intent = intent
//        )
    }
}