package com.example.crocodile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.crocodile.Activities.ResultActivity
import com.example.crocodile.Activities.RulesActivity
import com.example.crocodile.Activities.ScoreActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNewGame: Button = findViewById(R.id.new_game)
        val buttonRules: Button = findViewById(R.id.rules)
        val buttonContinueGame: Button = findViewById(R.id.continue_game)

        buttonNewGame.setOnClickListener {
            val intent = Intent(this, CommandsActivity::class.java)
            startActivity(intent)
        }

        buttonRules.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }

        buttonContinueGame.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }
}