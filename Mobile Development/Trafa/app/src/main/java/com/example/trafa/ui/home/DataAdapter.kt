package com.example.trafa.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trafa.Model.UlosItem
import com.example.trafa.R
import com.squareup.picasso.Picasso

class DataAdapter(private val dataList: List<UlosItem>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var itemClickListener: ((UlosItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (UlosItem) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(data)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        private val tvKelas: TextView = itemView.findViewById(R.id.tvKelas)
        private val ivGambar: ImageView = itemView.findViewById(R.id.ivGambar)

        fun bindData(data: UlosItem) {
            tvNama.text = data.nama
            tvKelas.text = data.kelas
            Picasso.get()
                .load(data.gambar)
                .into(ivGambar)
        }
    }
}