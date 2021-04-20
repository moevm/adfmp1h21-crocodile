package com.example.crocodile.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.crocodile.Helpers.OptionsHelper
import com.example.crocodile.R

class OptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivityForResult(intent, 1234)
        }

        val wordsToWinLabel: TextView = findViewById(R.id.num_fst)
        val wordsToWinSeekBar: SeekBar = findViewById(R.id.word_seek_bar)
        val currentWordsToWin = OptionsHelper.getWordsToWinPref(this)
        wordsToWinSeekBar.progress = currentWordsToWin
        wordsToWinLabel.text = currentWordsToWin.toString()
        wordsToWinSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                wordsToWinLabel.text = progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                OptionsHelper.saveWordsToWinPref(this@OptionsActivity, seekBar?.progress)
            }

        })

        val timesForRoundLabel: TextView = findViewById(R.id.num_snd)
        val timesForRoundSeekBar: SeekBar = findViewById(R.id.time_seek_bar)
        val currentTimesForRound = OptionsHelper.getTimesForRoundPref(this)
        timesForRoundSeekBar.progress = currentTimesForRound
        timesForRoundLabel.text = currentTimesForRound.toString()
        timesForRoundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                timesForRoundLabel.text = progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                OptionsHelper.saveTimesForRoundPref(this@OptionsActivity, seekBar?.progress)
            }

        })

        val skipSwitch: Switch = findViewById(R.id.skip_switch)
        skipSwitch.isChecked = OptionsHelper.getSkipSwitchPref(this)
        skipSwitch.setOnCheckedChangeListener { _, isChecked ->
            OptionsHelper.saveSkipSwitchPref(this, isChecked)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1234 && data != null) {
            if (data.getBooleanExtra(getString(R.string.back_to_main_page_key), true)) {
                finish();
            }
        }
    }
}