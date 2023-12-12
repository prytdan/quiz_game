package prytdan.quizgame.presentation.fragments.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import prytdan.quizgame.domain.models.Question
import prytdan.quizgame.domain.models.Questions
import prytdan.quizgame.domain.usecases.GetQuestionsUseCase
import prytdan.quizgame.presentation.fragments.BaseViewModel
import prytdan.quizgame.presentation.models.AnswersData
import prytdan.quizgame.util.Resource

class QuizViewModel(private val getQuestionsUseCase: GetQuestionsUseCase) : BaseViewModel() {

    private var fetchingJob: Job? = null

    private val userAnswers = mutableMapOf<Int, Int>()

    private val _questions = MutableLiveData<Resource<Questions>>()

    val questions: LiveData<Resource<Questions>> = _questions

    private val _allQuestionsAnswered = MutableLiveData<Boolean>()

    val allQuestionsAnswered: LiveData<Boolean> = _allQuestionsAnswered

    private val dispatchersIO = Dispatchers.IO

    fun fetchQuestions() {
        fetchingJob = viewModelScope.launch(dispatchersIO) {
            _questions.postValue(Resource.Loading())
            val response = getQuestionsUseCase.execute()
            _questions.postValue(response)
        }
    }

    fun selectOption(questionId: Int, selectedOptionIndex: Int) {
        userAnswers[questionId] = selectedOptionIndex
        checkAllQuestionsAnswered()
    }

    private fun checkAllQuestionsAnswered() {
        _allQuestionsAnswered.value = questions.value?.data?.questions?.size == userAnswers.size
    }

    fun saveAnswers(questions: List<Question>): AnswersData {
        val listOfUsersAnswersIds = mutableListOf<Int>()
        questions.forEachIndexed { index, _ ->
            userAnswers[index]?.let { listOfUsersAnswersIds.add(it) }
        }
        return AnswersData(questions, listOfUsersAnswersIds)
    }

    fun stopProcesses() {
        fetchingJob?.cancel()
    }

    fun getUserAnswers(): Map<Int, Int> {
        return userAnswers
    }
}