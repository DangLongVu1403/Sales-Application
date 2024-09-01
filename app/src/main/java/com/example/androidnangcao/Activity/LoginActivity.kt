package com.example.androidnangcao.Activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnToForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivityLoggedSales::class.java)
            startActivity(intent)
            finish()
        }

        var isPasswordVisible = false

        binding.tvInputPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.tvInputPassword.right - binding.tvInputPassword.compoundDrawables[2].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    if (isPasswordVisible) {
                        // Hiển thị mật khẩu
                        binding.tvInputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        binding.tvInputPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_password, 0, R.drawable.ic_eye, 0)
                    } else {
                        // Ẩn mật khẩu
                        binding.tvInputPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        binding.tvInputPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_password, 0, R.drawable.ic_hidden, 0)
                    }
                    // Đảm bảo con trỏ nằm ở cuối văn bản
                    binding.tvInputPassword.setSelection(binding.tvInputPassword.text?.length ?: 1)
                    return@setOnTouchListener false
                }
            }
            false
        }

    }
}