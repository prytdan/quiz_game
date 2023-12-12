package prytdan.quizgame.presentation.fragments.answers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import prytdan.quizgame.R
import prytdan.quizgame.databinding.AnswersItemBinding
import prytdan.quizgame.domain.models.Question

class AnswersListAdapter : ListAdapter<Question, AnswersListAdapter.ViewHolder>(DiffCallback()) {

    private var usersAnswersId: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnswersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = getItem(position)
        val userAnswerIndex = usersAnswersId[position]
        holder.bind(question, userAnswerIndex)
    }

    fun updateUsersAnswersId(list: List<Int>) {
        usersAnswersId = list
    }

    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: AnswersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question, userAnswerId: Int) {
            val correctAnswer = question.options[question.correctAnswerId]
            val userAnswer = question.options[userAnswerId]
            binding.apply {
                textQuestion.text = question.question
                textCorrectAnswer.text =
                    binding.root.context.getString(R.string.correct_answer, correctAnswer)
                textUsersAnswer.text =
                    binding.root.context.getString(R.string.user_answer, userAnswer)

                val answerColor = if (question.correctAnswerId == userAnswerId) {
                    ContextCompat.getColor(root.context, R.color.correct_answer_color)
                } else {
                    ContextCompat.getColor(root.context, R.color.incorrect_answer_color)
                }
                textUsersAnswer.setTextColor(answerColor)
            }
        }
    }
}