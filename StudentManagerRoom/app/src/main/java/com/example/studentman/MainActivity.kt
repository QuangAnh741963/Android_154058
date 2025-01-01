package com.example.studentman

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var customAdapter: CustomAdapter
    private lateinit var studentDao: StudentDao
    private val studentList = mutableListOf<Student>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = StudentDatabase.getDatabase(this)
        studentDao = database.studentDao()

        val lvSinhVien = findViewById<ListView>(R.id.lvSinhVien)
        customAdapter = CustomAdapter(this, studentList)
        lvSinhVien.adapter = customAdapter

        fetchStudents()

        val btnAddNew = findViewById<Button>(R.id.btnAddNew)
        btnAddNew.setOnClickListener {
            val name = findViewById<EditText>(R.id.edtAddNewName).text.toString()
            val studentId = findViewById<EditText>(R.id.edtAddNewMSSV).text.toString()
            if (name.isNotEmpty() && studentId.isNotEmpty()) {
                addStudent(Student(name = name, studentId = studentId))
            } else {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }

        lvSinhVien.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "You clicked: ${studentList[position].name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchStudents() {
        lifecycleScope.launch {
            val students = studentDao.getAllStudents()
            studentList.clear()
            studentList.addAll(students)
            customAdapter.notifyDataSetChanged()
        }
    }

    private fun addStudent(student: Student) {
        lifecycleScope.launch {
            studentDao.insert(student)
            fetchStudents()
        }
    }
}
