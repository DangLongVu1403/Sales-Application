package com.example.androidnangcao.Activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityInforStoreBinding

class InforStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInforStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInforStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.imgEditNameStore.setOnClickListener {
            showEditDialog(getString(R.string.name_store), binding.tvNameStore.text.toString()) { newValue ->
                binding.tvNameStore.text = newValue
            }
        }

        binding.imgEditAddress.setOnClickListener {
            showEditDialog(getString(R.string.address), binding.tvAddress.text.toString()) { newValue ->
                binding.tvAddress.text = newValue
            }
        }

        binding.imgEditAddressEmail.setOnClickListener {
            showEditDialog(getString(R.string.address_email), binding.tvAddressEmail.text.toString()) { newValue ->
                binding.tvAddressEmail.text = newValue
            }
        }

        binding.imgEditPhoneNumber.setOnClickListener {
            showEditDialog(getString(R.string.phone_number), binding.tvPhoneNumber.text.toString()) { newValue ->
                binding.tvPhoneNumber.text = newValue
            }
        }
    }

    private fun showEditDialog(fieldName: String, currentValue: String, onValueChanged: (String) -> Unit) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog_edit_single_field, null)
        val editField = dialogView.findViewById<EditText>(R.id.edit_field)
        val errorMessage = dialogView.findViewById<TextView>(R.id.tvWarning)

        editField.setText(currentValue)

        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.edit_title_format, fieldName))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                // Cập nhật giá trị nếu hợp lệ
                onValueChanged(editField.text.toString())
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()

        dialog.setOnShowListener {
            val saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            saveButton.isEnabled = false // Vô hiệu hóa nút "Lưu" lúc đầu

            editField.addTextChangedListener(object : TextWatcher {
                @SuppressLint("StringFormatInvalid")
                override fun afterTextChanged(s: Editable?) {
                    val newValue = s.toString().trim()
                    val isValid = when (fieldName) {
                        getString(R.string.address_email) -> isValidEmail(newValue)
                        getString(R.string.phone_number) -> isValidPhoneNumber(newValue)
                        else -> newValue.isNotEmpty()
                    }

                    if (isValid) {
                        errorMessage.visibility = View.GONE
                        saveButton.isEnabled = true
                    } else {
                        errorMessage.text = String.format(
                            getString(R.string.invalid),
                            fieldName)
                        errorMessage.visibility = View.VISIBLE
                        saveButton.isEnabled = false
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            saveButton.setOnClickListener {
                onValueChanged(editField.text.toString())
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    // Hàm kiểm tra định dạng email
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return email.matches(emailRegex.toRegex())
    }

    // Hàm kiểm tra định dạng số điện thoại Việt Nam
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberRegex = "^(0[1-9][0-9]{8}|\\+84[1-9][0-9]{8})$"
        return phoneNumber.matches(phoneNumberRegex.toRegex())
    }
}
