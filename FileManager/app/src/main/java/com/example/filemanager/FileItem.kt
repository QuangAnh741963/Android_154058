package com.example.filemanager

data class FileItem(
    val name: String,
    val isDirectory: Boolean,
    val path: String
)