package com.example.weathercompose.uitl

fun toTime(date: String): String {
    val item = date.dropLast(6).replace("T", " ")
    return item
}
