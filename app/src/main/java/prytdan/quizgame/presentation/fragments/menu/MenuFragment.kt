package prytdan.quizgame.presentation.fragments.menu

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import prytdan.quizgame.R
import prytdan.quizgame.databinding.FragmentMenuBinding
import prytdan.quizgame.presentation.fragments.BaseFragment
import prytdan.quizgame.util.DialogInflater.showDialogWithText
import prytdan.quizgame.util.InternetChecker.isInternetAvailable

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by viewModel()

    override fun getViewBinding() = FragmentMenuBinding.inflate(layoutInflater)

    override fun initViews() {
        setupControls()
    }

    private fun setupControls() {
        overrideBackPressed()
        binding.buttonStartQuiz.setOnClickListener {
            if (isInternetAvailable(requireContext())) findNavController().navigate(R.id.action_menuFragment_to_quizFragment)
            else showDialogWithText(
                layoutInflater,
                requireContext(),
                getString(R.string.check_internet)
            )
        }
        binding.buttonToThemesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_themeChoosingFragment)
        }
    }

    private fun overrideBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}