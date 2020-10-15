package com.example.translate.classes
//ARSALAN SHAKIL
//1910097



//object for the question  class
object Constants {

    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        //1
        var que1 = Question(
            1, "What does dog mean in finnish?",
            "koira",
            "kora",
            "koria",
            "koiraa",
            1
        )
        questionList.add(que1)

        //2
        que1 = Question(
            2, "What does book mean in finnish?",
            "kirja",
            "kiirja",
            "kirjä",
            "krja",
            1
        )
        questionList.add(que1)

        //3
        que1 = Question(
            3, "What does ball mean in finnish?",
            "palo",
            "pallo",
            "palloo",
            "pällo",
            2
        )
        questionList.add(que1)

        //4
        que1 = Question(
            4, "What does food mean in finnish?",
            "rauka",
            "ruokka",
            "ruokia",
            "ruokaa",
            4
        )
        questionList.add(que1)

        //5
        que1 = Question(
            5, "What does shoes mean in finnish?",
            "kengat",
            "kenkä",
            "kengät",
            "kengäät",
            3
        )
        questionList.add(que1)

        //6
        que1 = Question(
            6, "What does drink mean in finnish?",
            "juoda",
            "juodaa",
            "joda",
            "juda",
            1
        )
        questionList.add(que1)

        //7
        que1 = Question(
            7, "What does sleep mean in finnish?",
            "nukkuaa",
            "nukkuun",
            "nukkua",
            "nukkuin",
            3
        )
        questionList.add(que1)

        //8
        que1 = Question(
            8, "What does movie mean in finnish?",
            "elokuva",
            "elokuvia",
            "elokova",
            "elokkuva",
            1
        )
        questionList.add(que1)

        //9
        que1 = Question(
            9, "What does play mean in finnish?",
            "pelaan",
            "pelata",
            "pelasi",
            "pelaat",
            2
        )
        questionList.add(que1)

        return questionList
    }
}