package rs.rocketbyte.gym.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.usecase.example.WorkoutUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel()