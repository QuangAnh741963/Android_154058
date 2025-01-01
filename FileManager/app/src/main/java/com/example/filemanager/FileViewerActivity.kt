package com.example.filemanager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_viewer)

        val filePath = intent.getStringExtra("path")
        val fileContentTextView = findViewById<TextView>(R.id.textViewFileContent)

        if (filePath != null) {
            val file = File(filePath)
            if (file.exists()) {
                val content = file.readText()
                fileContentTextView.text = content
            } else {
                fileContentTextView.text = "File not found"
            }
        }
    }
}