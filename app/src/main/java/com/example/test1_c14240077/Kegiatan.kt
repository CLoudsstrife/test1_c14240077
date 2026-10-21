package test1.c14240077.app

import java.io.Serializable

data class Kegiatan(
    val kategori: String,
    val judul: String,
    val tagline: String,
    val tanggal: String,
    val jam: String,
    val lokasi: String,
    val deskripsi: String,
    val pembicara: String,
    val jabatan: String,
    val noPanitia: String,
    val gambar: Int,
    val tahun: Int,
    val bulan: Int,
    val hari: Int,
    val jamMulai: Int,
    val menitMulai: Int,
    val jamSelesai: Int,
    val menitSelesai: Int
) : Serializable