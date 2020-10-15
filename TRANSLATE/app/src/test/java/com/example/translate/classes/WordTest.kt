package com.example.translate.classes

import org.junit.Assert.*
import org.junit.Test

class WordTest {
    @Test
    fun checkDataInMeaning() {
        val test1 = Word("book", "kirjä")
        assertEquals("kirjä", test1.meaning)
    }

    @Test
    fun checkDataInWord() {
        val test2 = Word("ball", "pallo")
        assertEquals("ball", test2.word)
    }
}