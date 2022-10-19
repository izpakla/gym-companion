package rs.rocketbyte.gym.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.usecase.example.WorkoutUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel()