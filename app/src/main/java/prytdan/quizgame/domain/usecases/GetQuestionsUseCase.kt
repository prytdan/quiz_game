package prytdan.quizgame.domain.usecases

import prytdan.quizgame.domain.models.Questions
import prytdan.quizgame.domain.repository.QuestionsRepository
import prytdan.quizgame.util.Resource

class GetQuestionsUseCase(private val repository: QuestionsRepository) {

    suspend fun execute(): Resource<Questions> {
        return try {
            val response = repository.getQuestions()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}