package prytdan.quizgame.domain.repository

import prytdan.quizgame.domain.models.Questions

interface QuestionsRepository {

    suspend fun getQuestions(): Questions
}