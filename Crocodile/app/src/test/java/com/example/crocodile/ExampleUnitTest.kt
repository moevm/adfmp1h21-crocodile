package com.example.crocodile

import com.example.crocodile.Activities.PlayActivity
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
    fun scoreCalc() {
        assertEquals(4, PlayActivity().calcScore(true,5))
    }
}