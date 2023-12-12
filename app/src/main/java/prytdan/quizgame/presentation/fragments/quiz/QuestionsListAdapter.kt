package prytdan.quizgame.presentation.fragments.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import prytdan.quizgame.databinding.QuestionsItemBinding
import prytdan.quizgame.domain.models.Question

class QuestionsListAdapter(
    private val listener: OnOptionSelectedListener,
    private val selectedOptions: Map<Int, Int>
) : ListAdapter<Question, QuestionsListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            QuestionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = getItem(position)
        holder.bind(question)
    }

    class DiffCallback : DiffUtil.ItemCallback<Question>() {

        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: QuestionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val radioButtons =
            listOf(binding.option1, binding.option2, binding.option3, binding.option4)

        fun bind(question: Question) {
            binding.textQuestion.text = question.question
            question.options.forEachIndexed { index, option ->
                radioButtons[index].apply {
                    text = option
                    visibility = View.VISIBLE
                    setOnClickListener {
                        listener.onOptionSelected(bindingAdapterPosition, index)
                    }
                    isChecked = selectedOptions[bindingAdapterPosition] == index
                }
            }
        }
    }
}