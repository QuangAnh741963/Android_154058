package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencyconverter.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var toCurrency: Spinner
    private lateinit var textView: TextView
    private var keysList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toCurrency = findViewById(R.id.planets_spinner)
        val edtEuroValue = findViewById<EditText>(R.id.editText4)
        val btnConvert = findViewById<Button>(R.id.button)
        textView = findViewById(R.id.textView7)

        try {
            loadConvTypes()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        btnConvert.setOnClickListener {
            if (edtEuroValue.text.toString().isNotEmpty()) {
                val toCurr = toCurrency.selectedItem.toString()
                val euroValue = edtEuroValue.text.toString().toDoubleOrNull() ?: 0.0

                Toast.makeText(this@MainActivity, "Please Wait..", Toast.LENGTH_SHORT).show()
                try {
                    convertCurrency(toCurr, euroValue)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Please Enter a Value to Convert..", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadConvTypes() {
        val url = "https://api.exchangeratesapi.io/latest"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val message = e.message ?: "Error"
                Log.w("failure Response", message)
                runOnUiThread {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val message = response.body?.string() ?: ""
                runOnUiThread {
                    try {
                        val obj = JSONObject(message)
                        val rates = obj.getJSONObject("rates")

                        keysList = mutableListOf()
                        val keys = rates.keys()
                        while (keys.hasNext()) {
                            val key = keys.next() as String
                            keysList.add(key)
                        }

                        val spinnerAdapter = ArrayAdapter(
                            applicationContext,
                            android.R.layout.simple_spinner_item,
                            keysList
                        )
                        toCurrency.adapter = spinnerAdapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    private fun convertCurrency(toCurr: String, euroValue: Double) {
        val url = "https://api.exchangeratesapi.io/latest"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val message = e.message ?: "Error"
                Log.w("failure Response", message)
                runOnUiThread {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val message = response.body?.string() ?: ""
                runOnUiThread {
                    try {
                        val obj = JSONObject(message)
                        val rates = obj.getJSONObject("rates")
                        val rate = rates.getString(toCurr).toDoubleOrNull() ?: 0.0
                        val output = euroValue * rate
                        textView.text = output.toString()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}
