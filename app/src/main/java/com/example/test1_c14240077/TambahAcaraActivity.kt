package test1.c14240077.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TambahAcaraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_acara)
        setupEdgeToEdge(findViewById(R.id.main), findViewById(R.id.topBar))

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val etNama = findViewById<EditText>(R.id.etNama)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val etLokasi = findViewById<EditText>(R.id.etLokasi)

        findViewById<Button>(R.id.btnSimpan).setOnClickListener {
            val nama = etNama.text.toString().trim()
            if (nama.isEmpty()) {
                etNama.error = "Nama acara wajib diisi"
                return@setOnClickListener
            }

            AcaraStore.tambah(
                this,
                Acara(nama, etTanggal.text.toString().trim(), etLokasi.text.toString().trim())
            )
            Toast.makeText(this, "Acara berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}