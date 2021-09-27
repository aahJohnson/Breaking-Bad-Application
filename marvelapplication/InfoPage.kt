package com.example.marvelapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_page)

        val charName = findViewById<TextView>(R.id.char_name)

        charName.text = intent.getStringExtra("Name")

    }
}