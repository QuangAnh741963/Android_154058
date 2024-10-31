package com.example.basiclist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val showButton = findViewById<Button>(R.id.buttonShow)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupOptions)

        var mutableList: MutableList<Int> = mutableListOf()
        var showNumberFunc: (() -> Unit)? = null

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonL -> {
                    showNumberFunc = {
                        mutableList.clear()
                        val input = editText.text.toString()
                        val n = input.toIntOrNull() ?: 0
                        for (i in 1 until n) {
                            if (i % 2 == 1) {
                                mutableList.add(i)
                            }
                        }
                    }
                }
                R.id.radioButtonC -> {
                    showNumberFunc = {
                        mutableList.clear()
                        val input = editText.text.toString()
                        val n = input.toIntOrNull() ?: 0
                        for (i in 1 until n) {
                            if (i % 2 == 0) {
                                mutableList.add(i)
                            }
                        }
                    }
                }
                R.id.radioButtonCP -> {
                    showNumberFunc = {
                        mutableList.clear()
                        val input = editText.text.toString()
                        val n = input.toIntOrNull() ?: 0
                        for (i in 1 until n) {
                            val sqrt = Math.sqrt(i.toDouble()).toInt()
                            if (sqrt * sqrt == i) {
                                mutableList.add(i)
                            }
                        }
                    }
                }
            }
        }
        val lvNumber = findViewById<ListView>(R.id.lvNumber)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableList)
        lvNumber.adapter = adapter

        showButton.setOnClickListener {
            val input = editText.text.toString()
            val n = input.toIntOrNull()

            if (n == null || n <= 0) {
                textView.text = "Vui lòng nhập số nguyên dương n > 0"
                editText.text.clear()
            } else {
                showNumberFunc?.invoke()
                textView.text = ""
                adapter.notifyDataSetChanged()
            }
        }





    }
}