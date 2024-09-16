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
import com.example.androidnangcao.databinding.ActivityRegisterBinding
import com.example.androidnangcao.model.Request.RegisterRequest
import com.example.androidnangcao.model.Response.RegisterResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var inputValidator: validateUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputValidator = validateUser()  // Khởi tạo lớp validateUser

        // Thiết lập các TextWatcher cho các trường nhập liệu
        setupTextWatchers()

        binding.btnToLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            if (!inputValidator.validateUserName(binding.tvInputUserName.text.toString().trim(), binding.tvUserNameError)) {
                binding.tvUserNameError.visibility = View.VISIBLE
            } else if (!inputValidator.validateEmail(binding.tvInputEmail.text.toString().trim(), binding.tvEmailError)) {
                binding.tvEmailError.visibility = View.VISIBLE
            } else if (!inputValidator.validatePassword(binding.tvInputPassword.text.toString().trim(), binding.tvPasswordError)) {
                binding.tvPasswordError.visibility = View.VISIBLE
            } else if (!inputValidator.validateRepassword(binding.tvInputPassword.text.toString().trim(), binding.tvInputRepassword.text.toString().trim(), binding.tvRepasswordError)) {
                binding.tvRepasswordError.visibility = View.VISIBLE
            } else {
                val userName = binding.tvInputUserName.text.toString().trim()
                val email = binding.tvInputEmail.text.toString().trim()
                val password = binding.tvInputPassword.text.toString()

                // Tạo đối tượng RegisterRequest
                val registerRequest = RegisterRequest(username = userName, email = email, password = password)

                // Gọi API đăng ký với đối tượng RegisterRequest
                RetrofitClient.apiService.signup(registerRequest)
                    .enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                            if (response.isSuccessful) {
                                val registerResponse = response.body()
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                                Toast.makeText(this@RegisterActivity, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.e("aaa", "Error: ${response.code()}, Message: ${response.message()}")
                                Toast.makeText(this@RegisterActivity, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            Log.e("aaa", "Failure: ${t.message}")
                            Toast.makeText(this@RegisterActivity, "Đăng ký thất bại: ${t.message}", Toast.LENGTH_SHORT).show()
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
                    binding.tvInputRepassword.setSelection(binding.tvInputRepassword.text?.length ?: 1)
                    return@setOnTouchListener false
                }
            }
            false
        }
    }

    // Thiết lập các TextWatcher để kiểm tra trường người dùng đang nhập
    private fun setupTextWatchers() {
        binding.tvInputUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hiện TextView khi bắt đầu nhập
                binding.tvUserNameError.visibility = if (inputValidator.validateUserName(binding.tvInputUserName.text.toString().trim(), binding.tvUserNameError)) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.tvInputUserName.length()!=0){
                    binding.tvUserNameError.visibility = View.VISIBLE
                }else{
                    binding.tvUserNameError.visibility = View.GONE
                }
            }
        })

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

        binding.tvInputRepassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvRepasswordError.visibility = if (inputValidator.validateRepassword(binding.tvInputPassword.text.toString().trim(), binding.tvInputRepassword.text.toString().trim(), binding.tvRepasswordError )) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.tvInputRepassword.length()!=0){
                    binding.tvRepasswordError.visibility = View.VISIBLE
                }else{
                    binding.tvRepasswordError.visibility = View.GONE
                }
            }
        })

    }
}
