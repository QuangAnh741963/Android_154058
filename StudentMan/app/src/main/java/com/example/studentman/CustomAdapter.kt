package com.example.studentman

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class CustomAdapter(val activity: Activity, var list: List<OutData>) : ArrayAdapter<OutData>(activity, R.layout.list_item) {

    override fun getCount(): Int {
        return list.size    // Vẽ lên view tất cả các dòng của list được tạo ra
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = activity.layoutInflater  // Chuyển layout.xml thành View trong android
        val rowView = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        // Get ra id
        val name = rowView.findViewById<TextView>(R.id.name)
        val mssv = rowView.findViewById<TextView>(R.id.mssv)
        val btnEdit = rowView.findViewById<Button>(R.id.btnEdit)
        val btnDelete = rowView.findViewById<Button>(R.id.btnDelete)

        // Nạp phần tử của array vào id
        name.text = list[position].name
        mssv.text = list[position].mssv

        // Handle button click
        btnEdit.setOnClickListener {
            // Example action: Display a toast or open a dialog
            AlertDialog.Builder(activity).apply {
                setTitle("Họ và tên: ${list[position].name}")
                setMessage("MSSV: ${list[position].mssv}")
                setPositiveButton("Chỉnh sửa") { dialog, _ ->
                    dialog.dismiss()
                }
                setNegativeButton("Huỷ") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }

        // Handle button click
        btnDelete.setOnClickListener {
            // Example action: Display a toast or open a dialog
            AlertDialog.Builder(activity).apply {
                setTitle("Xoá sinh viên")
                setMessage("Bạn có chắc chắn muốn xoá sinh viên ${list[position].name} ?")
                setPositiveButton("Xoá") { dialog, _ ->
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

    // Hàm cập nhật danh sách và thông báo adapter rằng dữ liệu đã thay đổi
    fun updateList(newList: List<OutData>) {
        list = newList
        notifyDataSetChanged() // Gọi hàm này để cập nhật lại giao diện khi danh sách thay đổi
    }
}