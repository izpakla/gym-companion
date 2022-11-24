package rs.rocketbyte.gym.ui.startworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.databinding.FragmentStartWorkoutBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment
import rs.rocketbyte.gym.ui.main.MainViewModel

@AndroidEntryPoint
class StartWorkoutFragment : BindingFragment<FragmentStartWorkoutBinding>() {

    // Example use, remove if not needed
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: StartWorkoutViewModel by viewModels()

    private val workout: Workout by lazy { StartWorkoutFragmentArgs.fromBundle(requireArguments()).workout }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStartWorkoutBinding = FragmentStartWorkoutBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDescription.text = workout.description
        binding.buttonStartWorkout.setOnClickListener {
            findNavController().navigate(
                StartWorkoutFragmentDirections.actionStartWorkoutFragmentToDetailsFragment(
                    workout,
                    workout.name
                )
            )
        }
    }

}