package rs.rocketbyte.gym.ui.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.gym.databinding.FragmentDetailsBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment
import rs.rocketbyte.gym.ui.main.MainViewModel

@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>() {

    // Example use, remove if not needed
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*viewModel.workout.observe(viewLifecycleOwner) {
            binding.textTitle.text = it.name
        }*/

        viewModel.currentSession.observe(viewLifecycleOwner) {
            binding.textTitle.text = it.name
            binding.textReps.text = it.repsCount.toString()
            Glide.with(requireContext())
                .load(Uri.parse(it.image))
                .into(binding.imageExercise)
        }

        binding.buttonContinue.setOnClickListener {
            viewModel.nextSession()
        }
    }

}