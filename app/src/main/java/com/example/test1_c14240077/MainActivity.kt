package test1.c14240077.app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEdgeToEdge(findViewById(R.id.main), findViewById(R.id.topBar))

        findViewById<View>(R.id.cardSeminar).setOnClickListener { bukaDetail(0) }
        findViewById<View>(R.id.cardWorkshop).setOnClickListener { bukaDetail(1) }
        findViewById<View>(R.id.cardEvent).setOnClickListener { bukaDetail(2) }

        findViewById<Button>(R.id.btnTambahAcara).setOnClickListener {
            startActivity(Intent(this, TambahAcaraActivity::class.java))
        }

        findViewById<Button>(R.id.btnTentang).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        findViewById<Button>(R.id.btnDeveloper).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(AppConfig.EMAIL))
                putExtra(Intent.EXTRA_SUBJECT, "[Campus Activity] ask")
                putExtra(Intent.EXTRA_TEXT, "Saya ingin bertanya tentang tampilan aplikasi Campus Activity.")
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        tampilkanAcara()
    }

    private fun bukaDetail(index: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_KEGIATAN, DataKegiatan.daftar[index])
        startActivity(intent)
    }

    private fun tampilkanAcara() {
        val container = findViewById<LinearLayout>(R.id.containerAcara)
        val tvKosong = findViewById<TextView>(R.id.tvKosong)
        val daftar = AcaraStore.ambil(this)

        container.removeAllViews()
        tvKosong.visibility = if (daftar.isEmpty()) View.VISIBLE else View.GONE

        for (acara in daftar) {
            val item = layoutInflater.inflate(R.layout.item_acara, container, false)
            item.findViewById<TextView>(R.id.tvNamaAcara).text = acara.nama
            container.addView(item)
        }
    }
}