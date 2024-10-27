package com.example.gmail

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter (val activity: Activity, val list: List<OutData>) : ArrayAdapter<OutData>(activity, R.layout.list_item) {
    override fun getCount(): Int {
        return list.size    // Vẽ lên view tất cả các dòng của list được tạo ra
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater  // Chuyển layout.xml thành View trong android
        val rowView = contexts.inflate(R.layout.list_item, parent, false)

        // Get ra  id
        val avatar = rowView.findViewById<ImageView>(R.id.avatar)
        val imageView1 = rowView.findViewById<ImageView>(R.id.imageView1)
        val imageView2 = rowView.findViewById<ImageView>(R.id.imageView2)
        val title = rowView.findViewById<TextView>(R.id.title)
        val subTitle = rowView.findViewById<TextView>(R.id.subTitle)
        val content1 = rowView.findViewById<TextView>(R.id.content1)
        val content2 = rowView.findViewById<TextView>(R.id.content2)

        // Nạp phần tử của array vào id
        avatar.setImageResource(list[position].avatar)
        imageView1.setImageResource(list[position].imageView1)
        imageView2.setImageResource(list[position].imageView2)
        title.text = list[position].title
        subTitle.text = list[position].subTitle
        content1.text = list[position].content1
        content2.text = list[position].content2

        return rowView
    }


}