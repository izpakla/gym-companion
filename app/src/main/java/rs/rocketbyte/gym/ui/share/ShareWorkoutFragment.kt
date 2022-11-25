package rs.rocketbyte.gym.ui.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.databinding.FragmentShareWorkoutBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment

@AndroidEntryPoint
class ShareWorkoutFragment : BindingFragment<FragmentShareWorkoutBinding>() {

    private val viewModel: ShareWorkoutViewModel by viewModels()

    private val workout: Workout by lazy { ShareWorkoutFragmentArgs.fromBundle(requireArguments()).workout }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentShareWorkoutBinding = FragmentShareWorkoutBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.share(workout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.deleteShared(workout)
    }

}