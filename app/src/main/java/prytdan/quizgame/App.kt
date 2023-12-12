package prytdan.quizgame

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import prytdan.quizgame.di.dataModule
import prytdan.quizgame.di.domainModule
import prytdan.quizgame.di.presentationModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            modules(presentationModule)
            modules(dataModule)
            modules(domainModule)
        }
    }
}