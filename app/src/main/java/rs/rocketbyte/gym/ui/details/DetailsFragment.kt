package rs.rocketbyte.gym.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.workout.WorkoutState
import rs.rocketbyte.gym.R
import rs.rocketbyte.gym.commons.image.ImageLoader
import rs.rocketbyte.gym.databinding.FragmentDetailsBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment


@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>() {

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

        viewModel.canSkipExercise.observe(viewLifecycleOwner) {
            binding.textSkip.isVisible = it
        }

        viewModel.currentSession.observe(viewLifecycleOwner) {
            binding.textSkipSet.isInvisible = it is WorkoutState.Ready
            when (it) {
                is WorkoutState.LastSet -> updateSessionUi(it.session, it.position)
                is WorkoutState.Ready -> updateSessionUi(it.session, -1)
                is WorkoutState.Started -> updateSessionUi(it.session, it.position)
                else -> {}
            }
        }

        viewModel.currentImage.observe(viewLifecycleOwner) {
            ImageLoader.load(requireContext(), it, binding.imageExercise)
        }

        viewModel.nextState.observe(viewLifecycleOwner) {
            binding.buttonContinue.isEnabled = it.second
            if (it.second) {
                binding.textSkipSet.isInvisible = true
            }
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
        binding.textSet.text = getString(R.string.current_set, "${set + 1} / ${session.setCount}")
        binding.textReps.text = getString(R.string.set_reps, session.repsCount.toString())
        binding.textDuration.text = getString(R.string.set_duration, toMin(session.setDuration))
        binding.textDescription.text = session.description
    }

    private fun toMin(s: Int): String {
        val sec: Int = s % 60
        val min: Int = s / 60 % 60
        return "${min.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}"
    }

    private fun next(hasNext: Boolean) {
        if (!hasNext) {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }

}