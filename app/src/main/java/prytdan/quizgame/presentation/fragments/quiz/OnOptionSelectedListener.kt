package prytdan.quizgame.presentation.fragments.quiz

interface OnOptionSelectedListener {
    fun onOptionSelected(questionId: Int, selectedOptionIndex: Int)
}