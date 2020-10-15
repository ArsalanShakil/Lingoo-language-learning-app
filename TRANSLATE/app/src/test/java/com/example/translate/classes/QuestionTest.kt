package com.example.translate.classes

import org.junit.Assert.*
import org.junit.Test

class QuestionTest() {
    @Test
    fun create() {
        val test = Question(1, "What is dog in finnish", "koira", "kora", "kiora", "koria", 1)
        assertEquals(1, test.id)
        assertEquals("What is dog in finnish", test.question)
        assertEquals("koira", test.optionOne)
        assertEquals("kora", test.optionTwo)
        assertEquals("kiora", test.optionTree)
        assertEquals("koria", test.optionFour)
        assertEquals(1, test.correctAnswer)
    }
}