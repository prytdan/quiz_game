package prytdan.quizgame.domain.models

import com.google.gson.annotations.SerializedName

data class Question(
    val question: String,
    val options: List<String>,
    @SerializedName("correct_answer") val correctAnswerId: Int
)
