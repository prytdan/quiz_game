package prytdan.quizgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import prytdan.quizgame.presentation.fragments.BaseViewModel
import prytdan.quizgame.presentation.models.AnswersData

class SharedViewModel : BaseViewModel() {

    private val _quizData = MutableLiveData<AnswersData>()

    val quizData: LiveData<AnswersData> = _quizData

    private val _selectedTheme = MutableLiveData<Int>()

    val selectedTheme: LiveData<Int> = _selectedTheme

    fun updateAnswersData(data: AnswersData) {
        _quizData.value = data
    }

    fun selectTheme(drawableId: Int) {
        _selectedTheme.value = drawableId
    }
}