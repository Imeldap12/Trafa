package com.example.trafa.Model

data class UlosItem(

    val id: Int,
    val nama: String,
    val kelas: String,
    val gambar: String,
    val deskripsi: String

)

data class UlosResponse(val  tbl_desc : List<UlosItem>)
