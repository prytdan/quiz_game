package prytdan.quizgame.presentation.fragments.themechoosing

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import prytdan.quizgame.R
import prytdan.quizgame.databinding.FragmentThemeChoosingBinding
import prytdan.quizgame.presentation.SharedViewModel
import prytdan.quizgame.presentation.fragments.BaseFragment

class ThemeChoosingFragment : BaseFragment<FragmentThemeChoosingBinding, ThemeChoosingViewModel>() {

    override val viewModel: ThemeChoosingViewModel by viewModel()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun getViewBinding() = FragmentThemeChoosingBinding.inflate(layoutInflater)

    override fun initViews() {
        setupControls()
    }

    private fun setupControls() {
        overrideBackPressedToNavigateToFragment(R.id.action_themeChoosingFragment_to_menuFragment)
        binding.apply {
            buttonToMenu.setOnClickListener { findNavController().navigate(R.id.action_themeChoosingFragment_to_menuFragment) }
            imageTheme1.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_1) }
            imageTheme2.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_2) }
            imageTheme3.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_3) }
            imageTheme4.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_4) }
            imageTheme5.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_5) }
            imageTheme6.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_6) }
            imageTheme7.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_7) }
            imageTheme8.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_8) }
            imageTheme9.setOnClickListener { sharedViewModel.selectTheme(R.drawable.background_9) }
        }
    }
}