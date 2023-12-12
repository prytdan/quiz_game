package prytdan.quizgame.data.remote

import prytdan.quizgame.domain.models.Questions
import retrofit2.http.GET

interface QuestionsApi {

    @GET("v1/d81c4b21-7a92-4ed1-ad4e-61608c0007b9")
    suspend fun getQuestions(): Questions
}