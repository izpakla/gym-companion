package rs.rocketbyte.gym.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.usecase.workout.WorkoutUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {

    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>>
        get() = _workouts

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.Default) {
            val workouts = workoutUseCase.getAllWorkouts()
            _workouts.postValue(workouts)
        }
    }

}