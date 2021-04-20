package com.example.crocodile

import com.example.crocodile.Activities.PlayActivity
import com.example.crocodile.Helpers.SharedPreferenceHelper
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun timeFormat_isCorrect() {
        val time = PlayActivity().rightTimer(60000)
        assertEquals("01:60", time)
    }

    @Test
    fun differentWords_isCorrect() {
        val arrayOfWords: List<String> = PlayActivity().getFiveDifferentWords()
        val newArrayOfWords: MutableList<String> = ArrayList()
        for (item in arrayOfWords) {
            if (!newArrayOfWords.contains(item)) {
                newArrayOfWords.add(item)
            }
        }
        assertEquals(arrayOfWords.size, newArrayOfWords.size)
    }

    @Test
    fun calcScore_isCorrect_first() {
        val oldScore = 4
        val newScore = PlayActivity().calcScore(true, 5, oldScore, true)
        assertEquals(0, newScore)
    }

    @Test
    fun calcScore_isCorrect_second() {
        val oldScore = 6
        val newScore = PlayActivity().calcScore(true, 5, oldScore, true)
        assertEquals(1, newScore)
    }

    @Test
    fun calcScore_isCorrect_third() {
        val oldScore = 6
        val newScore = PlayActivity().calcScore(false, 5, oldScore, true)
        assertEquals(11, newScore)
    }
}