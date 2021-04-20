package com.example.crocodile.Helpers

import android.app.Activity
import android.content.Context
import com.example.crocodile.R

class CurrentGameStatsHelper {
    companion object Factory {

        fun swapCurrentTeamQueue(context: Context) {
            val currentTeamIndex = (getCurrentTeamIndex(context)+1)%2
            saveCurrentTeamIndex(currentTeamIndex, context)
        }

        private fun saveCurrentTeamIndex(index: Int, context: Context) {
            SharedPreferenceHelper.saveNumberToSharedPref(context,
                index,
                context.getString(R.string.current_team_index_key),
                0
            )
        }
        private fun getCurrentTeamIndex(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                context.getString(R.string.current_team_index_key),
                0
            )
        }
        fun getIsGameCanFinish(context: Context): Boolean {
            return getCurrentTeamIndex(context) == 1
        }

        fun getCurrentTeamName(context: Context): String {
            return context.resources.getStringArray(R.array.teamNames)[getCurrentTeamIndex(context)]
        }
        fun getAnotherTeamName(context: Context): String {
            return context.resources.getStringArray(R.array.teamNames)[(getCurrentTeamIndex(context)+1)%2]
        }

        fun saveCurrentTeamScore(context: Context, score: Int) {
            SharedPreferenceHelper.saveNumberToSharedPref(context,
                getCurrentTeamScore(context) + score,
                getCurrentTeamName(context),
                0
            )
        }
        fun getCurrentTeamScore(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                getCurrentTeamName(context),
                0
            )
        }
        fun getAnotherTeamScore(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                getAnotherTeamName(context),
                0
            )
        }

        fun getTeamScore(context: Context, teamIndex: Int): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                context.resources.getStringArray(R.array.teamNames)[teamIndex],
                0
            )
        }

        fun saveIncreasedRoundNumber(context: Context) {
            SharedPreferenceHelper.saveNumberToSharedPref(context,
                getRoundNumber(context) + 1,
                context.getString(R.string.round_number_key),
                1
            )
        }
        fun getRoundNumber(context: Context): Int {
            return SharedPreferenceHelper.getNumberFromSharedPref(context,
                context.getString(R.string.round_number_key),
                1
            )
        }

    }
}