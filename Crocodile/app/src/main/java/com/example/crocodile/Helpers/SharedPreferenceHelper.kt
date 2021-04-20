package com.example.crocodile.Helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.crocodile.R

class SharedPreferenceHelper {
    companion object Factory {
        fun saveNumberToSharedPref(context: Context, number: Int?, key: String, def: Int) {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putInt(key, number ?: def)
                apply()
            }
        }
        fun getNumberFromSharedPref(context: Context, key: String, def: Int): Int {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getInt(key, def)
        }

        fun saveBoolToSharedPref(context: Context, bool: Boolean, key: String) {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putBoolean(key, bool)
                apply()
            }
        }
        fun getBoolFromSharedPref(context: Context, key: String): Boolean {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getBoolean(key, false)
        }

        fun clearSharedPref(context: Context) {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                clear()
                commit()
            }
        }
        fun safeSharedPrefIsRun (context: Context) {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putBoolean(context.getString(R.string.sp_empty_key), true)
                apply()
            }
        }
        fun getSharedPrefIsRun (context: Context): Boolean {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.shared_preferences_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getBoolean(context.getString(R.string.sp_empty_key), false)
        }
    }
}