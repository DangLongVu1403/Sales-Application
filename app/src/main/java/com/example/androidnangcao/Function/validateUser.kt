package com.example.androidnangcao.Function

import android.widget.TextView
import com.example.androidnangcao.R

class validateUser {

    private val regexUsername = Regex("^[a-zA-Z0-9._-]+$")
    private val regexPassword = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")

    fun validateUserName(userName: String, userNameError: TextView): Boolean {
        return if (userName.isEmpty()) {
            userNameError.text = userNameError.context.getString(R.string.error_empty_username)
            false
        } else if (!regexUsername.matches(userName)) {
            userNameError.text = userNameError.context.getString(R.string.error_invalid_username)
            false
        } else {
            userNameError.text = ""
            true
        }
    }

    fun validateEmail(email: String, emailError: TextView): Boolean {
        return if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.text = emailError.context.getString(R.string.error_invalid_email)
            false
        } else {
            emailError.text = ""
            true
        }
    }

    fun validatePassword(password: String, passwordError: TextView): Boolean {
        return if (password.isEmpty()) {
            passwordError.text = passwordError.context.getString(R.string.error_empty_password)
            false
        } else if (!regexPassword.matches(password)) {
            passwordError.text = passwordError.context.getString(R.string.error_invalid_password)
            false
        } else {
            passwordError.text = ""
            true
        }
    }

    fun validateRepassword(password: String, repassword: String, repasswordError: TextView): Boolean {
        return if (password.isEmpty()) {
            repasswordError.text = repasswordError.context.getString(R.string.error_empty_repassword)
            false
        } else if (repassword != password) {
            repasswordError.text = repasswordError.context.getString(R.string.error_password_not_match)
            false
        } else {
            repasswordError.text = ""
            true
        }
    }
}