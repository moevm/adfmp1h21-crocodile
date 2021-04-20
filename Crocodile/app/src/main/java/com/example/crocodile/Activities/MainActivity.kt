package com.example.crocodile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crocodile.Activities.OptionsActivity
import com.example.crocodile.Activities.ResultActivity
import com.example.crocodile.Activities.RulesActivity
import com.example.crocodile.Activities.ScoreActivity
import com.example.crocodile.Helpers.CurrentGameStatsHelper
import com.example.crocodile.Helpers.SharedPreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var buttonContinueGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNewGame: Button = findViewById(R.id.new_game)
        val buttonRules: Button = findViewById(R.id.rules)
        buttonContinueGame = findViewById(R.id.continue_game)

        buttonNewGame.setOnClickListener {
            SharedPreferenceHelper.clearSharedPref(this)
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }

        buttonRules.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }

        buttonContinueGame.setOnClickListener {
            if (SharedPreferenceHelper.getSharedPrefIsRun(this)) {
                val intent = Intent(this, ScoreActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.cant_continue_game), Toast.LENGTH_LONG).show()
            }
        }
    }
}