package com.example.crocodile.Helpers

import android.app.Activity
import android.content.Context
import com.example.crocodile.R

class OptionsHelper {
    companion object Factory {
        fun saveWordsToWinPref(context: Context, number: Int?) {
            SharedPreferenceHelper.saveNumberToSharedPref(
                    context,
                    number,
                    context.getString(R.string.words_to_win_key),
                    context.resources.getInteger(R.integer.words_to_win_def)
            )
        }
        fun getWordsToWinPref(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                    context.getString(R.string.words_to_win_key),
                    context.resources.getInteger(R.integer.words_to_win_def)
            )
        }

        fun saveTimesForRoundPref(context: Context, number: Int?) {
            SharedPreferenceHelper.saveNumberToSharedPref(
                    context,
                    number,
                    context.getString(R.string.times_for_round_key),
                    context.resources.getInteger(R.integer.times_for_round_def)
            )
        }
        fun getTimesForRoundPref(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(
                    context,
                    context.getString(R.string.times_for_round_key),
                    context.resources.getInteger(R.integer.times_for_round_def)
            )
        }

        fun saveSkipSwitchPref(context: Context, value: Boolean) {
            SharedPreferenceHelper.saveBoolToSharedPref(
                    context,
                    value,
                    context.getString(R.string.pass_penalty_key)
            )
        }
        fun getSkipSwitchPref(context: Context): Boolean {
            return SharedPreferenceHelper.getBoolFromSharedPref(
                    context,
                    context.getString(R.string.pass_penalty_key)
            )
        }
    }
}