package com.example.androidnangcao.Activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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
                    binding.tvInputPassword.setSelection(binding.tvInputPassword.text?.length ?: 0)
                    return@setOnTouchListener false
                }
            }
            false
        }
        binding.tvInputRepassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.tvInputRepassword.right - binding.tvInputRepassword.compoundDrawables[2].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    if (isPasswordVisible) {
                        // Hiển thị mật khẩu
                        binding.tvInputRepassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        binding.tvInputRepassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_password, 0, R.drawable.ic_eye, 0)
                    } else {
                        // Ẩn mật khẩu
                        binding.tvInputRepassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        binding.tvInputRepassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_password, 0, R.drawable.ic_hidden, 0)
                    }
                    // Đảm bảo con trỏ nằm ở cuối văn bản
                    binding.tvInputRepassword.setSelection(binding.tvInputRepassword.text?.length ?: 0)
                    return@setOnTouchListener false
                }
            }
            false
        }

    }
}