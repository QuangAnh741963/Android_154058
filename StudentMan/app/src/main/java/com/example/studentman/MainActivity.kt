package com.example.studentman

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
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

        val btnAddNew = findViewById<Button>(R.id.btnAddNew)
        btnAddNew.setOnClickListener {
            val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            dialog.apply {
                setTitle("Thêm sinh viên vào danh sách")
                setMessage("Bạn có chắc chắn muốn thêm sinh viên này vào danh sách?")
                setNegativeButton("No") { dialogInterface, _ -> dialogInterface.dismiss() }
                setPositiveButton("Yes") { dialogInterface, _ -> dialogInterface.dismiss() }
                setCancelable(false)
            }
            dialog.show()
        }



    }
}