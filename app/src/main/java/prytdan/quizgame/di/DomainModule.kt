package prytdan.quizgame.di

import org.koin.dsl.module
import prytdan.quizgame.domain.usecases.GetQuestionsUseCase

val domainModule = module {

    factory { GetQuestionsUseCase(repository = get()) }
}