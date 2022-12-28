package rs.rocketbyte.gym.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.databinding.FragmentHomeBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment
import rs.rocketbyte.gym.ui.main.MainViewModel

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    // Example use, remove if not needed
    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        HomeAdapter(object : HomeAdapter.OnItemAction<Workout> {
            override fun onSelect(item: Workout) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToStartWorkoutFragment(item, item.name)
                )
            }

            override fun onShare(item: Workout) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToShareWorkoutFragment(
                        item,
                        item.name
                    )
                )
            }

        })
    }


    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.workouts.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

}