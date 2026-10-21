package test1.c14240077.app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import java.util.Calendar
import java.util.TimeZone

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEGIATAN = "extra_kegiatan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupEdgeToEdge(findViewById(R.id.main), findViewById(R.id.topBar))

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val kegiatan = IntentCompat.getSerializableExtra(
            intent, EXTRA_KEGIATAN, Kegiatan::class.java
        )
        if (kegiatan == null) {
            finish()
            return
        }

        findViewById<ImageView>(R.id.ivGambar).setImageResource(kegiatan.gambar)
        findViewById<TextView>(R.id.tvKategori).text = kegiatan.kategori
        findViewById<TextView>(R.id.tvJudul).text = kegiatan.judul
        findViewById<TextView>(R.id.tvTagline).text = kegiatan.tagline
        findViewById<TextView>(R.id.tvTanggal).text = "📅  ${kegiatan.tanggal}"
        findViewById<TextView>(R.id.tvJam).text = "🕘  ${kegiatan.jam}"
        findViewById<TextView>(R.id.tvLokasi).text = "📍  ${kegiatan.lokasi}"
        findViewById<TextView>(R.id.tvDeskripsi).text = kegiatan.deskripsi
        findViewById<TextView>(R.id.tvPembicara).text = kegiatan.pembicara
        findViewById<TextView>(R.id.tvJabatan).text = kegiatan.jabatan

        findViewById<Button>(R.id.btnKalender).setOnClickListener {
            val zona = TimeZone.getTimeZone("Asia/Jakarta")
            val mulai = Calendar.getInstance(zona).apply {
                set(kegiatan.tahun, kegiatan.bulan - 1, kegiatan.hari,
                    kegiatan.jamMulai, kegiatan.menitMulai, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val selesai = Calendar.getInstance(zona).apply {
                set(kegiatan.tahun, kegiatan.bulan - 1, kegiatan.hari,
                    kegiatan.jamSelesai, kegiatan.menitSelesai, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val intent = Intent(Intent.ACTION_INSERT).apply {
                data = CalendarContract.Events.CONTENT_URI
                putExtra(CalendarContract.Events.TITLE, kegiatan.judul)
                putExtra(
                    CalendarContract.Events.DESCRIPTION,
                    "[Jadwal - ${AppConfig.NRP}] . ${kegiatan.judul}"
                )
                putExtra(CalendarContract.Events.EVENT_LOCATION, kegiatan.lokasi)
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, mulai.timeInMillis)
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, selesai.timeInMillis)
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi kalender", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnPanitia).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${kegiatan.noPanitia}")
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi telepon", Toast.LENGTH_SHORT).show()
            }
        }
    }
}