package com.example.filemanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class FileAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    private val fileList = mutableListOf<FileItem>()

    fun submitList(newFiles: List<FileItem>) {
        fileList.clear()
        fileList.addAll(newFiles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val fileItem = fileList[position]
        holder.bind(fileItem)
    }

    override fun getItemCount(): Int = fileList.size

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.textViewFileName)

        fun bind(fileItem: FileItem) {
            textView.text = fileItem.name
            itemView.setOnClickListener { listener.onItemClick(fileItem) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(fileItem: FileItem)
    }
}