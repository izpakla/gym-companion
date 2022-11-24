package rs.rocketbyte.gym.ui.details

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.workout.WorkoutSession
import rs.rocketbyte.core.workout.WorkoutState
import rs.rocketbyte.gym.R
import rs.rocketbyte.gym.commons.text.TextProvider
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val textProvider: TextProvider
) : ViewModel() {

    private val _workout = MutableLiveData<Workout>()
    val workout: LiveData<Workout>
        get() = _workout

    private val _currentSession = MutableLiveData<WorkoutState>()
    val currentSession: LiveData<WorkoutState>
        get() = _currentSession

    private val _nextState = MutableLiveData<Pair<String, Boolean>>()
    val nextState: LiveData<Pair<String, Boolean>>
        get() = _nextState

    private val _canSkipExercise = MutableLiveData(true)
    val canSkipExercise: LiveData<Boolean>
        get() = _canSkipExercise

    private var workoutSession: WorkoutSession? = null
    private var countDownTimer: CountDownTimer? = null

    fun nextSession(): Boolean {
        val state = workoutSession?.getNextSession() ?: return false
        loadState(state)
        return true
    }

    fun nextStep(): Boolean {
        val state = workoutSession?.getNextState() ?: return false
        loadState(state)
        return true
    }

    private fun loadState(state: WorkoutState) {
        _currentSession.value = state

        when (state) {
            is WorkoutState.Ready -> {
                stopTimer()
                _nextState.postValue(Pair(textProvider.getText(R.string.exercise_start), true))
            }
            is WorkoutState.Started -> startTimer(
                state.session.setDuration,
                textProvider.getText(R.string.exercise_next_set)
            )
            is WorkoutState.LastSet -> startTimer(
                state.session.setDuration,
                textProvider.getText(R.string.exercise_next)
            )
            is WorkoutState.FinishedWorkout -> {
                _canSkipExercise.value = false
                startTimer(
                    state.session.setDuration,
                    textProvider.getText(R.string.exercise_close)
                )
            }
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun startTimer(duration: Int, message: String) {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(duration * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _nextState.postValue(Pair("${(millisUntilFinished / 1000) + 1}", false))
            }

            override fun onFinish() {
                _nextState.postValue(Pair(message, true))
            }
        }.apply {
            start()
        }
    }

    fun loadWorkout(workout: Workout) {
        if (_workout.value == null) {
            workoutSession = WorkoutSession(workout)
            _workout.value = workout
            nextStep()
        }
    }
}