package com.example.androidnangcao.Activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.androidnangcao.databinding.ActivityStatisticalRevenueBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StatisticalRevenueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticalRevenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticalRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        val currentDate = getCurrentDate()
        binding.edtFromDate.setText(currentDate)
        binding.edtToDate.setText(currentDate)

        // Sự kiện khi nhấn vào EditText từ ngày
        binding.edtFromDate.setOnClickListener {
            showDatePicker(binding.edtFromDate, null, binding.edtToDate.text.toString())
        }

        // Sự kiện khi nhấn vào EditText đến ngày
        binding.edtToDate.setOnClickListener {
            showDatePicker(binding.edtToDate, binding.edtFromDate.text.toString(), null)
        }
    }

    // Hàm lấy ngày hiện tại
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }

    // Hàm hiển thị DatePicker và thiết lập ngày vào EditText
    private fun showDatePicker(editText: EditText, minDate: String?, maxDate: String?) {
        val calendar = Calendar.getInstance()

        // Lấy giá trị từ EditText và thiết lập ngày mặc định cho DatePicker
        val currentText = editText.text.toString()
        if (currentText.isNotEmpty()) {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            try {
                calendar.time = sdf.parse(currentText) ?: Calendar.getInstance().time
            } catch (e: Exception) {
                e.printStackTrace()
                // Nếu lỗi phân tích cú pháp, thiết lập lại ngày hiện tại
                calendar.time = Calendar.getInstance().time
            }
        }

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        // Giới hạn ngày tối đa là ngày hiện tại
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

        // Thiết lập ngày tối thiểu nếu có
        minDate?.let {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            try {
                datePickerDialog.datePicker.minDate = sdf.parse(it)?.time ?: 0L
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Thiết lập ngày tối đa nếu có
        maxDate?.let {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            try {
                datePickerDialog.datePicker.maxDate = sdf.parse(it)?.time ?: Calendar.getInstance().timeInMillis
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        datePickerDialog.show()
    }
}
