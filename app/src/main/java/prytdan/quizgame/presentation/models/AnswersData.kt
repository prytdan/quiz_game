package prytdan.quizgame.presentation.models

import prytdan.quizgame.domain.models.Question

data class AnswersData(
    val questions: List<Question>,
    val usersAnswersId: List<Int>
)
