package com.example.trafa.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trafa.R
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var ivGambar: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvKelas: TextView
    private lateinit var tvDeskripsi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ivGambar = findViewById(R.id.ivGambar)
        tvNama = findViewById(R.id.tvNama)
        tvKelas = findViewById(R.id.tvKelas)
        tvDeskripsi = findViewById(R.id.tvDeskripsi)

        val nama = intent.getStringExtra("nama")
        val kelas = intent.getStringExtra("kelas")
        val gambar = intent.getStringExtra("gambar")
        val deskripsi = intent.getStringExtra("deskripsi")

        tvNama.text = nama
        tvKelas.text = kelas
        tvDeskripsi.text = deskripsi

        Picasso.get()
            .load(gambar)
            .into(ivGambar)
    }

}
