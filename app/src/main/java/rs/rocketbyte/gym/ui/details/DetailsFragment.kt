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
import rs.rocketbyte.core.model.Exercise
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.workout.WorkoutState
import rs.rocketbyte.gym.R
import rs.rocketbyte.gym.databinding.FragmentDetailsBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment
import rs.rocketbyte.gym.ui.details.pager.ImageStateAdapter


@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()

    private val session: Session by lazy { DetailsFragmentArgs.fromBundle(requireArguments()).session }

    private val imageStateAdapter by lazy { ImageStateAdapter(this) }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assetMultipleImages.viewPager.adapter = imageStateAdapter

        /*viewModel.workout.observe(viewLifecycleOwner) {
            binding.textTitle.text = it.name
        }*/

        viewModel.canSkipExercise.observe(viewLifecycleOwner) {
            binding.textSkip.isVisible = it
        }

        viewModel.currentSession.observe(viewLifecycleOwner) {
            binding.textSkipSet.isInvisible = it is WorkoutState.Ready
            when (it) {
                is WorkoutState.LastSet -> updateSessionUi(it.exercise, it.position)
                is WorkoutState.Ready -> updateSessionUi(it.exercise, -1)
                is WorkoutState.Started -> updateSessionUi(it.exercise, it.position)
                else -> {}
            }
        }

        viewModel.currentImage.observe(viewLifecycleOwner) {
            imageStateAdapter.setItems(it)
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

        viewModel.loadWorkout(session)
    }

    private fun updateSessionUi(session: Exercise, set: Int) {
        binding.textTitle.text = session.name
        binding.textSet.text = getString(R.string.current_set, "${set + 1} / ${session.setCount}")
        binding.textReps.text = getString(R.string.set_reps, session.repsCount.toString())
        binding.textDuration.text =
            getString(R.string.set_duration, viewModel.secToMin(session.setDuration))
        binding.textDescription.text = session.description
    }

    private fun next(hasNext: Boolean) {
        if (!hasNext) {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }

}