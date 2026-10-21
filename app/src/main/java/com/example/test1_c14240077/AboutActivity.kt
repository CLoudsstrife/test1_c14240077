package test1.c14240077.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupEdgeToEdge(findViewById(R.id.main), findViewById(R.id.topBar))

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<TextView>(R.id.tvNama).text = AppConfig.NAMA
        findViewById<TextView>(R.id.tvNrp).text = AppConfig.NRP
        findViewById<TextView>(R.id.tvEmail).text = AppConfig.EMAIL
        findViewById<TextView>(R.id.tvHp).text = AppConfig.NO_HP
    }
}