package com.example.maheromadan.utils

object GlobalMethods {

    fun convertEnToBn(data: String): String? {
        return data.replace("0".toRegex(), "\u09E6")
            .replace("1".toRegex(), "\u09E7")
            .replace("2".toRegex(), "\u09E8")
            .replace("3".toRegex(), "\u09E9")
            .replace("4".toRegex(), "\u09EA")
            .replace("5".toRegex(), "\u09EB")
            .replace("6".toRegex(), "\u09EC")
            .replace("7".toRegex(), "\u09ED")
            .replace("8".toRegex(), "\u09EE")
            .replace("9".toRegex(), "\u09EF")
    }
}