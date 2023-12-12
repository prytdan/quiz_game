package prytdan.quizgame.data.repository

import prytdan.quizgame.data.remote.QuestionsApi
import prytdan.quizgame.domain.models.Questions
import prytdan.quizgame.domain.repository.QuestionsRepository

class QuestionsRepositoryImpl(private val api: QuestionsApi) : QuestionsRepository {

    override suspend fun getQuestions(): Questions {
        return api.getQuestions()
    }
}