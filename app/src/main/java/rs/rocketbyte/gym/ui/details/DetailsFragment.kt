package rs.rocketbyte.gym.ui.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.workout.WorkoutState
import rs.rocketbyte.gym.databinding.FragmentDetailsBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment
import rs.rocketbyte.gym.ui.main.MainViewModel

@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>() {

    // Example use, remove if not needed
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()

    private val workout: Workout by lazy { DetailsFragmentArgs.fromBundle(requireArguments()).workout }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadWorkout(workout)

        /*viewModel.workout.observe(viewLifecycleOwner) {
            binding.textTitle.text = it.name
        }*/

        viewModel.currentSession.observe(viewLifecycleOwner) {
            binding.textSkipSet.isInvisible =
                it is WorkoutState.FinishedWorkout || it is WorkoutState.Ready
            when (it) {
                is WorkoutState.LastSet -> updateSessionUi(it.session, it.position)
                is WorkoutState.Ready -> updateSessionUi(it.session, -1)
                is WorkoutState.Started -> updateSessionUi(it.session, it.position)
                else -> {}
            }
        }

        viewModel.nextState.observe(viewLifecycleOwner) {
            binding.buttonContinue.isEnabled = it.second
            binding.buttonContinue.text = it.first
        }

        binding.textSkipSet.setOnClickListener {
            next(viewModel.nextStep())
        }

        binding.buttonContinue.setOnClickListener {
            next(viewModel.nextStep())
        }

        binding.textSkip.setOnClickListener {
            next(viewModel.nextSession())
        }
    }

    private fun updateSessionUi(session: Session, set: Int) {
        binding.textTitle.text = session.name
        binding.textReps.text = session.repsCount.toString()
        binding.textSet.text = "${set + 1} / ${session.setCount}"
        Glide.with(requireContext())
            .load(Uri.parse(session.image))
            .into(binding.imageExercise)
    }

    private fun next(hasNext: Boolean) {
        if (!hasNext) {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }

}