package com.example.studentman

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    var list = mutableListOf<OutData>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contextMenu = findViewById<TextView>(R.id.contextMenu)
        registerForContextMenu(contextMenu)

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

        lvSinhVien.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(
                this,
                "You choose: ${list[i]}",
                Toast.LENGTH_LONG
            ).show()
        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fl1, sub1)
//            commit()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuAdd -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl1, sub1)
                    commit()
                }
                showToast("Option: Add New selected")
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(3, 31, 1, "Edit")
        menu?.add(3, 31, 2, "Remove")
        menu?.setHeaderTitle("Sinh viên: Nguyễn Văn A")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    val sub1 = Fragment1()


}
