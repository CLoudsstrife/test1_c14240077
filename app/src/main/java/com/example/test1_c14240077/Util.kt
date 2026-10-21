package test1.c14240077.app

import android.graphics.Color
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun AppCompatActivity.setupEdgeToEdge(root: View, topBar: View) {
    enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
    val topAwal = topBar.paddingTop
    ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
        topBar.updatePadding(top = topAwal + bars.top)
        root.updatePadding(
            left = bars.left,
            right = bars.right,
            bottom = maxOf(bars.bottom, ime.bottom)
        )
        insets
    }
}