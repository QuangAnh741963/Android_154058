package com.example.liststudent_test2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

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

        // Nạp phần tử của array vào id
        name.text = list[position].name
        mssv.text = list[position].mssv

        return rowView
    }

    // Hàm cập nhật danh sách và thông báo adapter rằng dữ liệu đã thay đổi
    fun updateList(newList: List<OutData>) {
        list = newList
        notifyDataSetChanged() // Gọi hàm này để cập nhật lại giao diện khi danh sách thay đổi
    }
}