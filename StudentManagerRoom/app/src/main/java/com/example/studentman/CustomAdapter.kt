package com.example.studentman

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CustomAdapter(
    private val context: Context, // Thay vì Activity, dùng Context để mở rộng khả năng sử dụng
    private var studentList: MutableList<OutData> // Sử dụng MutableList để dễ dàng thay đổi danh sách
) : ArrayAdapter<OutData>(context, R.layout.list_item) {

    override fun getCount(): Int = studentList.size

    override fun getItem(position: Int): OutData = studentList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Chuyển layout.xml thành View
        val inflater = LayoutInflater.from(context)
        val rowView = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        // Lấy các View từ layout
        val nameTextView = rowView.findViewById<TextView>(R.id.name)
        val mssvTextView = rowView.findViewById<TextView>(R.id.mssv)
        val btnEdit = rowView.findViewById<Button>(R.id.btnEdit)
        val btnDelete = rowView.findViewById<Button>(R.id.btnDelete)

        // Lấy dữ liệu từ danh sách
        val student = studentList[position]
        nameTextView.text = student.name
        mssvTextView.text = student.mssv

        // Xử lý sự kiện khi nhấn nút "Chỉnh sửa"
        btnEdit.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Họ và tên: ${student.name}")
                setMessage("MSSV: ${student.mssv}")
                setPositiveButton("Chỉnh sửa") { dialog, _ ->
                    // Hành động chỉnh sửa
                    dialog.dismiss()
                }
                setNegativeButton("Huỷ") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }

        // Xử lý sự kiện khi nhấn nút "Xoá"
        btnDelete.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Xoá sinh viên")
                setMessage("Bạn có chắc chắn muốn xoá sinh viên ${student.name}?")
                setPositiveButton("Xoá") { dialog, _ ->
                    // Xoá sinh viên khỏi danh sách và cập nhật giao diện
                    (context as Activity).lifecycleScope.launch {
                        studentList.removeAt(position) // Xoá khỏi danh sách
                        notifyDataSetChanged() // Cập nhật giao diện
                        Toast.makeText(context, "Đã xoá sinh viên: ${student.name}", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
                setNegativeButton("Huỷ") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }

        return rowView
    }

    // Hàm cập nhật danh sách
    fun updateList(newList: List<OutData>) {
        studentList.clear()
        studentList.addAll(newList)
        notifyDataSetChanged() // Thông báo cập nhật giao diện
    }
}
