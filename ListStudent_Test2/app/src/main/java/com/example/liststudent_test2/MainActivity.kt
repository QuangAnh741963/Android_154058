package com.example.liststudent_test2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    var list = mutableListOf<OutData>()
    var filteredList = mutableListOf<OutData>() // Danh sách để lưu trữ kết quả tìm kiếm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<EditText>(R.id.editText)

        // Khởi tạo danh sách ban đầu
        list.add(OutData("Nguyễn Văn A", "20201234"))
        list.add(OutData("Nguyễn Văn B", "20202342"))
        list.add(OutData("Nguyễn Văn C", "20205467"))
        list.add(OutData("Nguyễn Văn D", "20213456"))
        list.add(OutData("Nguyễn Văn E", "20213456"))

        // Cập nhật adapter với danh sách ban đầu
        customAdapter = CustomAdapter(this, list)
        val lvSinhVien = findViewById<ListView>(R.id.lvSinhVien)
        lvSinhVien.adapter = customAdapter

        // Thiết lập TextWatcher cho EditText để theo dõi sự thay đổi
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Lọc danh sách dựa trên nội dung nhập vào
                val searchText = s.toString().lowercase()
                filteredList.clear() // Xóa danh sách kết quả cũ
                for (item in list) {
                    if (item.name.lowercase().contains(searchText) || item.mssv.contains(searchText)) {
                        filteredList.add(item)
                    }
                }

                // Cập nhật adapter với danh sách lọc
                customAdapter.updateList(filteredList)
            }
        })
    }
}