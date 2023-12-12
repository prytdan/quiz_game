package prytdan.quizgame.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import prytdan.quizgame.presentation.SharedViewModel
import prytdan.quizgame.presentation.fragments.answers.AnswersViewModel
import prytdan.quizgame.presentation.fragments.menu.MenuViewModel
import prytdan.quizgame.presentation.fragments.quiz.QuizViewModel
import prytdan.quizgame.presentation.fragments.themechoosing.ThemeChoosingViewModel

val presentationModule = module {
    viewModel { MenuViewModel() }
    viewModel { QuizViewModel(getQuestionsUseCase = get()) }
    viewModel { AnswersViewModel() }
    viewModel { ThemeChoosingViewModel() }
    viewModel { SharedViewModel() }
}