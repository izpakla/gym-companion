package rs.rocketbyte.gym.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.usecase.workout.WorkoutUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {

    private val _workout = MutableLiveData<Workout>()
    val workout: LiveData<Workout>
        get() = _workout

    private val _currentSession = MutableLiveData<Session>()
    val currentSession: LiveData<Session>
        get() = _currentSession

    private var sessionIndex = 0

    fun nextSession() {
        nextSession(_workout.value?.session?.getOrNull(sessionIndex))
    }

    private fun nextSession(session: Session? = null) {
        val s = session ?: return
        sessionIndex++
        _currentSession.postValue(s)
    }

    fun loadWorkout(workout: Workout) {
        if (_workout.value == null) {
            sessionIndex = 0
            _workout.postValue(workout)
            nextSession(workout.session.first())
        }
    }
}