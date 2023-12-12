package prytdan.quizgame.di

import org.koin.dsl.module
import prytdan.quizgame.data.remote.QuestionsApi
import prytdan.quizgame.data.repository.QuestionsRepositoryImpl
import prytdan.quizgame.domain.repository.QuestionsRepository
import prytdan.quizgame.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionsApi::class.java)
    }

    single<QuestionsRepository> { QuestionsRepositoryImpl(api = get()) }
}