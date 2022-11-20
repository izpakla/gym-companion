package rs.rocketbyte.gym.ui.details

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.workout.WorkoutSession
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _workout = MutableLiveData<Workout>()
    val workout: LiveData<Workout>
        get() = _workout

    private val _currentSession = MutableLiveData<Pair<Session, Int>>()
    val currentSession: LiveData<Pair<Session, Int>>
        get() = _currentSession

    private val _nextState = MutableLiveData<Pair<String, Boolean>>()
    val nextState: LiveData<Pair<String, Boolean>>
        get() = _nextState

    private var workoutSession: WorkoutSession? = null
    private var countDownTimer: CountDownTimer? = null

    fun next(): Boolean {
        val state = workoutSession?.next() ?: return false
        loadState(state)
        return true
    }

    private fun loadState(state: Pair<Session, Int>, start: Boolean = true) {
        _currentSession.value = state
        if (start) {
            val session = state.first
            countDownTimer?.cancel()
            countDownTimer = object : CountDownTimer(session.restDuration * 1000L, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    _nextState.postValue(Pair("${millisUntilFinished / 1000}", false))
                }

                override fun onFinish() {
                    _nextState.postValue(Pair("Next", true))
                }
            }.apply {
                start()
            }
        }
    }

    fun loadWorkout(workout: Workout) {
        if (_workout.value == null) {
            workoutSession = WorkoutSession(workout)
            _workout.value = workout
            workoutSession?.getCurrentState()?.let {
                loadState(it, false)
            }
        }
    }
}