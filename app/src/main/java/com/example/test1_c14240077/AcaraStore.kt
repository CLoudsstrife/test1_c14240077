package test1.c14240077.app

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object AcaraStore {
    private const val PREF = "acara_pref"
    private const val KEY = "daftar_acara"

    fun ambil(context: Context): List<Acara> {
        val json = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getString(KEY, "[]") ?: "[]"
        val arr = JSONArray(json)
        val hasil = mutableListOf<Acara>()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            hasil.add(Acara(o.getString("nama"), o.optString("tanggal"), o.optString("lokasi")))
        }
        return hasil
    }

    fun tambah(context: Context, acara: Acara) {
        val arr = JSONArray()
        for (a in ambil(context) + acara) {
            arr.put(
                JSONObject()
                    .put("nama", a.nama)
                    .put("tanggal", a.tanggal)
                    .put("lokasi", a.lokasi)
            )
        }
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putString(KEY, arr.toString()).apply()
    }
}