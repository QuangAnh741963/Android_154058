package com.example.filemanager

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class MainActivity : AppCompatActivity(), FileAdapter.OnItemClickListener {
    private lateinit var fileAdapter: FileAdapter
    private var currentPath: String = Environment.getExternalStorageDirectory().absolutePath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request permissions
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            setupRecyclerView()
            loadFiles(currentPath)
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your external storage.",
                123,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fileAdapter = FileAdapter(this)
        recyclerView.adapter = fileAdapter
    }

    private fun loadFiles(path: String) {
        val file = File(path)
        val files = file.listFiles()
        val fileItems = files?.map {
            FileItem(it.name, it.isDirectory, it.absolutePath)
        } ?: emptyList()
        fileAdapter.submitList(fileItems)
    }

    override fun onItemClick(fileItem: FileItem) {
        if (fileItem.isDirectory) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("path", fileItem.path)
            startActivity(intent)
        } else {
            val intent = Intent(this, FileViewerActivity::class.java)
            intent.putExtra("path", fileItem.path)
            startActivity(intent)
        }
    }
}
