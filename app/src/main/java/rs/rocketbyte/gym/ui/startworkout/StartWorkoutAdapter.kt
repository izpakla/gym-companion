package rs.rocketbyte.gym.ui.startworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.databinding.ItemStartWorkoutActionBinding
import rs.rocketbyte.gym.databinding.ItemStartWorkoutHeaderBinding

class StartWorkoutAdapter(
    private val workout: Workout,
    private val onSessionClick: (Session) -> Unit
) : RecyclerView.Adapter<StartWorkoutAdapter.SwViewHolder>() {


    sealed class SwViewHolder(root: View) : ViewHolder(root) {
        class Header(val binding: ItemStartWorkoutHeaderBinding) :
            SwViewHolder(binding.root)

        class Action(
            val binding: ItemStartWorkoutActionBinding,
            private val onSessionClick: (Session) -> Unit
        ) :
            SwViewHolder(binding.root) {
            init {
                binding.action.setOnClickListener {
                    val session = it.tag as? Session ?: return@setOnClickListener
                    onSessionClick(session)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            else -> TYPE_ACTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> SwViewHolder.Header(
                ItemStartWorkoutHeaderBinding.inflate(inflater, parent, false)
            )
            TYPE_ACTION -> SwViewHolder.Action(
                ItemStartWorkoutActionBinding.inflate(inflater, parent, false),
                onSessionClick
            )
            else -> throw IllegalStateException("StartWorkoutAdapter viewType=$viewType not handled.")
        }
    }

    override fun onBindViewHolder(holder: SwViewHolder, position: Int) {
        when (holder) {
            is SwViewHolder.Header -> {
                with(holder) {
                    binding.textDescription.text = workout.description
                }
            }
            is SwViewHolder.Action -> {
                with(holder) {
                    with(workout.sessions[position - 1]) {
                        binding.action.text = name
                        binding.action.tag = this
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = workout.sessions.size + 1

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ACTION = 1
    }
}