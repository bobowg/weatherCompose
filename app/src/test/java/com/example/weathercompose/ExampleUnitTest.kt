package com.example.weathercompose

import com.example.weathercompose.data.WeatherIcon
import com.example.weathercompose.uitl.toTime
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
    fun toTimeTest() {
        val date = "2020-06-30T22:00+08:00"
        val b = toTime(date)
        val a = "2020-06-30 22:00"
        println(b)
        assertEquals(a, b)
    }

    @Test
    fun WeatherIconTest() {
        val a = 500
        var b = 0
        for (i in WeatherIcon.entries) {
            if (a === i.int){
                assertEquals(a, i.int)
                break
            }

        }
    }
}