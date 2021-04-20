package com.example.crocodile.Activities

import android.app.Activity
import com.example.crocodile.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.crocodile.Helpers.CurrentGameStatsHelper
import com.example.crocodile.Helpers.SharedPreferenceHelper

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        SharedPreferenceHelper.safeSharedPrefIsRun(this)
        updateData()
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        val firstTeamScore: TextView = findViewById(R.id.fst_team_score)
        firstTeamScore.text = CurrentGameStatsHelper.getTeamScore(this, 0).toString()

        val secondTeamScore: TextView = findViewById(R.id.snd_team_score)
        secondTeamScore.text = CurrentGameStatsHelper.getTeamScore(this, 1).toString()

        val roundNumber: TextView = findViewById(R.id.round)
        roundNumber.text = getString(R.string.round_label) + " " + CurrentGameStatsHelper.getRoundNumber(this).toString()

        val currentTeam: TextView = findViewById(R.id.team_name_round)
        currentTeam.text = CurrentGameStatsHelper.getCurrentTeamName(this)
    }

    override fun onBackPressed() {
        val answerIntent = Intent()

        val bundle = Bundle()
        bundle.putBoolean(getString(R.string.back_to_main_page_key), true)

        answerIntent.putExtras(bundle)
        setResult(Activity.RESULT_OK, answerIntent)

        super.onBackPressed()
    }

}