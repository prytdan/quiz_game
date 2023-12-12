package prytdan.quizgame.presentation.fragments.quiz

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import prytdan.quizgame.R
import prytdan.quizgame.databinding.FragmentQuizBinding
import prytdan.quizgame.domain.models.Questions
import prytdan.quizgame.presentation.SharedViewModel
import prytdan.quizgame.presentation.fragments.BaseFragment
import prytdan.quizgame.util.DialogInflater.showDialogWithText
import prytdan.quizgame.util.Resource

class QuizFragment : BaseFragment<FragmentQuizBinding, QuizViewModel>(), OnOptionSelectedListener {

    override val viewModel: QuizViewModel by viewModel()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val listAdapter: QuestionsListAdapter by lazy {
        QuestionsListAdapter(
            this,
            viewModel.getUserAnswers()
        )
    }

    override fun getViewBinding() = FragmentQuizBinding.inflate(layoutInflater)

    override fun initViews() {
        subscribeToViewModel()
        setupViews()
        setupControls()
        viewModel.fetchQuestions()
    }

    override fun onOptionSelected(questionId: Int, selectedOptionIndex: Int) {
        viewModel.selectOption(questionId, selectedOptionIndex)
    }

    private fun setupControls() {
        overrideBackPressed()
        binding.buttonAnswer.setOnClickListener {
            val savedAnswers = viewModel.saveAnswers(listAdapter.currentList)
            sharedViewModel.updateAnswersData(savedAnswers)
            findNavController().navigate(R.id.action_quizFragment_to_answersFragment)
        }
    }

    private fun setupViews() {
        binding.recyclerViewQuestions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    private fun subscribeToViewModel() {
        viewModel.questions.observe(viewLifecycleOwner) { resource ->
            onResourceReceive(resource)
        }
        viewModel.allQuestionsAnswered.observe(viewLifecycleOwner) { allAnswered ->
            binding.buttonAnswer.isEnabled = allAnswered
        }
    }

    private fun onResourceReceive(resource: Resource<Questions>) {
        when (resource) {
            is Resource.Loading -> {
                setLoadingLayout()
            }

            is Resource.Success -> {
                listAdapter.submitList(resource.data?.questions)
                setLoadedLayout()
            }

            is Resource.Error -> {
                setLoadedLayout()
                val errorDialog = showDialogWithText(
                    layoutInflater,
                    requireContext(),
                    getString(R.string.error_occurred)
                )
                errorDialog.setOnCancelListener {
                    // For improvement: to process different types of errors
                    viewModel.stopProcesses()
                    findNavController().navigate(R.id.action_quizFragment_to_menuFragment)
                }
            }
        }
    }

    private fun setLoadingLayout() {
        binding.apply {
            buttonAnswer.visibility = View.GONE
            recyclerViewQuestions.visibility = View.GONE
            progressBarLoading.visibility = View.VISIBLE
        }
    }

    private fun setLoadedLayout() {
        binding.apply {
            buttonAnswer.visibility = View.VISIBLE
            recyclerViewQuestions.visibility = View.VISIBLE
            progressBarLoading.visibility = View.GONE
        }
    }

    private fun overrideBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.stopProcesses()
                findNavController().navigate(R.id.action_quizFragment_to_menuFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}