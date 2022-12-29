package rs.rocketbyte.gym.ui.startworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.databinding.FragmentStartWorkoutBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment

@AndroidEntryPoint
class StartWorkoutFragment : BindingFragment<FragmentStartWorkoutBinding>() {

    private val workout: Workout by lazy { StartWorkoutFragmentArgs.fromBundle(requireArguments()).workout }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStartWorkoutBinding = FragmentStartWorkoutBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = StartWorkoutAdapter(workout) {
            findNavController().navigate(
                StartWorkoutFragmentDirections.actionStartWorkoutFragmentToDetailsFragment(
                    it,
                    it.name
                )
            )
        }
    }

}