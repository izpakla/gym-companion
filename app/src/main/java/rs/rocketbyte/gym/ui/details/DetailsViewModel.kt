package rs.rocketbyte.gym.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rs.rocketbyte.core.usecase.workout.WorkoutUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {
    fun load() {
        viewModelScope.launch(Dispatchers.Default) {
            workoutUseCase.getAllWorkouts().forEach {
                Log.d("DetailsViewModel", "$it")
            }
        }
    }
}