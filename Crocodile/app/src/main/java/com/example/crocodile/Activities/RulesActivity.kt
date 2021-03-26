package com.example.crocodile.Activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.crocodile.R

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        val backButton: Button = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}