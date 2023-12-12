package prytdan.quizgame.presentation.fragments.answers

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import prytdan.quizgame.R
import prytdan.quizgame.databinding.FragmentAnswersBinding
import prytdan.quizgame.presentation.SharedViewModel
import prytdan.quizgame.presentation.fragments.BaseFragment
import prytdan.quizgame.util.DialogInflater.showDialogWithText
import prytdan.quizgame.util.InternetChecker.isInternetAvailable


class AnswersFragment : BaseFragment<FragmentAnswersBinding, AnswersViewModel>() {

    override val viewModel: AnswersViewModel by viewModel()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val listAdapter: AnswersListAdapter = AnswersListAdapter()

    override fun getViewBinding() = FragmentAnswersBinding.inflate(layoutInflater)

    override fun initViews() {
        setupViews()
        subscribeToViewModel()
        setupControls()
    }

    private fun subscribeToViewModel() {
        sharedViewModel.quizData.observe(viewLifecycleOwner) { answers ->
            listAdapter.updateUsersAnswersId(answers.usersAnswersId)
            listAdapter.submitList(answers.questions)
        }
    }

    private fun setupViews() {
        binding.recyclerViewAnswers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun setupControls() {
        overrideBackPressedToNavigateToFragment(R.id.action_answersFragment_to_menuFragment)
        binding.buttonPlayAgain.setOnClickListener {
            if (isInternetAvailable(requireContext())) findNavController().navigate(R.id.action_answersFragment_to_quizFragment)
            else showDialogWithText(
                layoutInflater,
                requireContext(),
                getString(R.string.check_internet)
            )
        }
        binding.buttonToMenu.setOnClickListener {
            findNavController().navigate(R.id.action_answersFragment_to_menuFragment)
        }
    }
}