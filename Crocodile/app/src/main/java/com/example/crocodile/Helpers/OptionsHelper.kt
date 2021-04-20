package com.example.crocodile.Helpers

import android.app.Activity
import android.content.Context
import com.example.crocodile.R

class OptionsHelper {
    companion object Factory {
        fun saveWordsToWinPref(activity: Activity, number: Int?) {
            SharedPreferenceHelper.saveNumberToSharedPref(activity,
                number,
                activity.getString(R.string.words_to_win_key),
                activity.resources.getInteger(R.integer.words_to_win_def)
            )
        }
        fun getWordsToWinPref(activity: Activity): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(activity,
                activity.getString(R.string.words_to_win_key),
                activity.resources.getInteger(R.integer.words_to_win_def)
            )
        }

        fun saveTimesForRoundPref(activity: Activity, number: Int?) {
            SharedPreferenceHelper.saveNumberToSharedPref(activity,
                number,
                activity.getString(R.string.times_for_round_key),
                activity.resources.getInteger(R.integer.times_for_round_def)
            )
        }
        fun getTimesForRoundPref(activity: Activity): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(activity,
                activity.getString(R.string.times_for_round_key),
                activity.resources.getInteger(R.integer.times_for_round_def)
            )
        }

        fun saveSkipSwitchPref(activity: Activity, value: Boolean) {
            SharedPreferenceHelper.saveBoolToSharedPref(activity,
                value,
                activity.getString(R.string.pass_penalty_key)
            )
        }
        fun getSkipSwitchPref(activity: Activity): Boolean {
            return SharedPreferenceHelper.getBoolFromSharedPref(activity,
                activity.getString(R.string.pass_penalty_key)
            )
        }
    }
}