package com.example.gmail

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Khởi tạo muộn, chỉ càn khai báo mà không cần gán giá trị
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Khai báo list_item
        var list = mutableListOf<OutData>()
        list.add(OutData(R.drawable.avatar01, R.drawable.arrow, R.drawable.star, "Edurila.com", "12:34 PM", "$19 Only (First 10 spots) - Bestselling...", "Are you looking to Learn Web Designin..."))
        list.add(OutData(R.drawable.avatar02, R.drawable.arrow, R.drawable.star, "Chris Abad", "12:34 PM", "Help make Campaign Monitor better", "Let us know your thoughts! No Images..."))
        list.add(OutData(R.drawable.avatar03, R.drawable.arrow, R.drawable.star, "Tuto.com", "12:34 PM", "8h de formation gratuite et les nouvea...", "Photoshop, SEO, Blender, CSS, WordPre..."))
        list.add(OutData(R.drawable.avatar04, R.drawable.arrow, R.drawable.star, "support", "12:34 PM", "Société Ovh: suivi de vos services - hp...", "SAS OVH - http://www.ovh.com 2 rue K..."))
        list.add(OutData(R.drawable.avatar05, R.drawable.arrow, R.drawable.star, "Matt from lonic", "12:34 PM", "The New lonic Creator Is Here!", "Are you looking to Learn Web Designin..."))

        // Cập nhật adapter
        customAdapter = CustomAdapter(this, list)
        // khai báo listView để hiển thị
        val lvGmail = findViewById<ListView>(R.id.lvGmail)
        lvGmail.adapter = customAdapter

    }
}