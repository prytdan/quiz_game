package prytdan.quizgame.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import prytdan.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private companion object {
        const val THEME_PREFERENCES = "ThemePreferences"
        const val SELECTED_THEME = "SelectedTheme"
    }

    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val sharedPreferences by lazy { getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSavedThemeFromSharedPref()
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        sharedViewModel.selectedTheme.observe(this) { drawableId ->
            binding.root.setBackgroundResource(drawableId)
            sharedPreferences.edit().putInt(SELECTED_THEME, drawableId).apply()
        }
    }

    private fun setSavedThemeFromSharedPref() {
        val savedThemeId = sharedPreferences.getInt(SELECTED_THEME, -1)
        if (savedThemeId != -1) binding.root.setBackgroundResource(savedThemeId)
    }
}