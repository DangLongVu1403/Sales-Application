package com.example.androidnangcao.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnangcao.Function.validateUser
import com.example.androidnangcao.Object.RetrofitClient
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityLoginBinding
import com.example.androidnangcao.model.Request.LoginRequest
import com.example.androidnangcao.model.Response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var inputValidator: validateUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inputValidator = validateUser()
        setupTextWatchers()
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
            if (!inputValidator.validateEmail(
                    binding.tvInputEmail.text.toString().trim(),
                    binding.tvEmailError
                )
            ) {
                binding.tvEmailError.visibility = View.VISIBLE
            } else if (!inputValidator.validatePassword(
                    binding.tvInputPassword.text.toString().trim(), binding.tvPasswordError
                )
            ) {
                binding.tvPasswordError.visibility = View.VISIBLE
            } else{
                val email = binding.tvInputEmail.text.toString().trim()
                val password = binding.tvInputPassword.text.toString()
                val loginRequest = LoginRequest(email = email, password = password)

                // Gọi API đăng nhập và xử lý phản hồi
                RetrofitClient.apiService.signin(loginRequest)
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                val loginResponse = response.body()!!
                                // Xử lý phản hồi thành công và chuyển sang MainActivityLoggedSales
                                val intent =
                                    Intent(this@LoginActivity, MainActivityLoggedSales::class.java)
                                startActivity(intent)
                                finish()
                                Log.d("Login", "Token: ${loginResponse.accessToken}")
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Đăng nhập thành công!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Xử lý lỗi từ phản hồi không thành công
                                Log.e(
                                    "Login",
                                    "Error: ${response.code()}, Message: ${response.message()}"
                                )
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Đăng nhập thất bại!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Xử lý lỗi khi không thể kết nối đến server
                            Log.e("Login", "Failure: ${t.message}")
                            Toast.makeText(
                                this@LoginActivity,
                                "Đăng nhập thất bại: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
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
    private fun setupTextWatchers() {
        binding.tvInputEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hiện TextView khi bắt đầu nhập
                binding.tvEmailError.visibility = if (inputValidator.validateEmail(binding.tvInputEmail.text.toString().trim(), binding.tvEmailError)) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.tvInputEmail.length()!=0){
                    binding.tvEmailError.visibility = View.VISIBLE
                }else{
                    binding.tvEmailError.visibility = View.GONE
                }
            }
        })

        binding.tvInputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hiện TextView khi bắt đầu nhập
                binding.tvPasswordError.visibility = if (inputValidator.validatePassword(binding.tvInputPassword.text.toString().trim(), binding.tvPasswordError)) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.tvInputPassword.length()!=0){
                    binding.tvPasswordError.visibility = View.VISIBLE
                }else{
                    binding.tvPasswordError.visibility = View.GONE
                }
            }
        })
    }
}