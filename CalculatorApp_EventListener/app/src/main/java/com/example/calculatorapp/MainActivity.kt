package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtScreen = findViewById<TextView>(R.id.txtScreen)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        val btnSum = findViewById<Button>(R.id.btnSum)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnCal = findViewById<Button>(R.id.btnCal)

        // Khai báo các biến lưu trữ giá trị và toán tử
        var value1: Double = 0.0
        var value2: Double = 0.0
        var operator: String? = null

// Lắng nghe các sự kiện click
        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.btn7 -> {
                    txtScreen.text = txtScreen.text.toString() + "7"
                    if (operator == null) {
                        value1 = txtScreen.text.toString().toDouble()
                    } else {
                        value2 =
                            txtScreen.text.toString().replaceBefore(operator ?: "", "").toDouble()
                    }
                }

                R.id.btn8 -> {
                    txtScreen.text = txtScreen.text.toString() + "8"
                    if (operator == null) {
                        value1 = txtScreen.text.toString().toDouble()
                    } else {
                        value2 =
                            txtScreen.text.toString().replaceBefore(operator ?: "", "").toDouble()
                    }
                }

                R.id.btnSum -> {
                    operator = "+"
                    txtScreen.text = txtScreen.text.toString() + " + "
                }

                R.id.btnCal -> {
                    val result = when (operator) {
                        "+" -> value1 + value2
                        "-" -> value1 - value2
                        "*" -> value1 * value2
                        "/" -> value1 / value2
                        else -> 0.0
                    }

                    // Hiển thị phép tính và kết quả trên txtScreen
                    txtScreen.text = "${value1.toInt()} $operator ${value2.toInt()} = $result"
                }
                // Có thể thêm các nút khác như btnSub, btnMul, btnDiv theo cách tương tự
            }
        }

// Gán clickListener cho các button
        btn7.setOnClickListener(clickListener)
        btn8.setOnClickListener(clickListener)
        btnSum.setOnClickListener(clickListener)
        btnCal.setOnClickListener(clickListener)

// Các button khác như btnSub, btnMul, btnDiv cũng gán tương tự


    }
}