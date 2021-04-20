package com.example.crocodile.Activities

import com.example.crocodile.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.crocodile.Helpers.CurrentGameStatsHelper
import com.example.crocodile.Helpers.SharedPreferenceHelper
import com.example.crocodile.MainActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val winTeamLabel: TextView = findViewById(R.id.t_name_fst)
        winTeamLabel.text = CurrentGameStatsHelper.getCurrentTeamName(this)

        val winTeamScore: TextView = findViewById(R.id.count_fst)
        winTeamScore.text = CurrentGameStatsHelper.getCurrentTeamScore(this).toString()

        val loseTeamLabel: TextView = findViewById(R.id.t_name_snd)
        loseTeamLabel.text = CurrentGameStatsHelper.getAnotherTeamName(this)

        val loseTeamScore: TextView = findViewById(R.id.count_snd)
        loseTeamScore.text = CurrentGameStatsHelper.getAnotherTeamScore(this).toString()

        SharedPreferenceHelper.clearSharedPref(this)
    }
}