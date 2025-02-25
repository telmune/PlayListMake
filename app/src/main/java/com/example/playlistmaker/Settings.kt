package com.example.playlistmaker

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import android.widget.Button


class Settings : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val image2 = findViewById<ImageButton>(R.id.butshare)
        image2.setOnClickListener {
            shareText("Вот моя программа")
        }

        val image3 = findViewById<ImageButton>(R.id.butsup)
        image3.setOnClickListener {
            val image3Intent = Intent(Intent.ACTION_SENDTO)
            image3Intent.data = Uri.parse("mailto:")
            image3Intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("Rachet31@yandex.ru"))
            startActivity(image3Intent)
        }


        val image4 = findViewById<ImageButton>(R.id.butpol)
        image4.setOnClickListener() {
            val image4Intent = Intent(this, ActivityMessage::class.java)
            startActivity(image4Intent)
        }

    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Выберите приложение для отправки"))


        }

    }

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_settings)
        sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        updateTheme(isDarkMode)
        val themeToggleButton = findViewById<ImageButton>(R.id.butlist)
        themeToggleButton.setOnClickListener {
            val newMode = !sharedPreferences.getBoolean("isDarkMode", false)
            saveThemePreference(newMode)
            updateTheme(newMode)
        }
    }

    private fun saveThemePreference(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkMode", isDarkMode)
        editor.apply()
    }

    private fun updateTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Log.d("ThemeSwitcher", "Current XML: darksettings.xml")
            Toast.makeText(this, "Current XML: darksettings.xml", Toast.LENGTH_SHORT).show()

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Log.d("ThemeSwitcher", "Current XML: activity_settings.xml")
            Toast.makeText(this, "Current XML: activity_settings.xml", Toast.LENGTH_SHORT).show()


        }
    }
}
