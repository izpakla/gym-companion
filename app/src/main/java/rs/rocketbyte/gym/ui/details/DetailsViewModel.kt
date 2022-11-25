package rs.rocketbyte.gym.ui.details

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.audio.BeepPlayer
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.workout.WorkoutSession
import rs.rocketbyte.core.workout.WorkoutState
import rs.rocketbyte.gym.R
import rs.rocketbyte.gym.commons.text.TextProvider
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val textProvider: TextProvider,
    private val beepPlayer: BeepPlayer
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

    private val _currentImage = MutableLiveData<String>()
    val currentImage: LiveData<String>
        get() = _currentImage

    private var workoutSession: WorkoutSession? = null
    private var sessionCountDownTimer: CountDownTimer? = null
    private var imageCountDownTimer: CountDownTimer? = null

    fun nextSession(): Boolean {
        beepPlayer.stop()
        val state = workoutSession?.getNextSession() ?: return false
        loadState(state)
        return true
    }

    fun nextStep(): Boolean {
        beepPlayer.stop()
        val state = workoutSession?.getNextState() ?: return false
        loadState(state)
        return true
    }

    private fun loadState(state: WorkoutState) {
        _currentSession.value = state
        startImageTransition(state)

        when (state) {
            is WorkoutState.Ready -> {
                stopSessionTimer()
                _nextState.postValue(Pair(textProvider.getText(R.string.exercise_start), true))
            }
            is WorkoutState.Started -> startSessionTimer(
                state.session.setDuration,
                textProvider.getText(R.string.exercise_next_set)
            )
            is WorkoutState.LastSet -> startSessionTimer(
                state.session.setDuration,
                textProvider.getText(R.string.exercise_next)
            )
            is WorkoutState.FinishedWorkout -> {
                _canSkipExercise.value = false
                startSessionTimer(
                    state.session.setDuration,
                    textProvider.getText(R.string.exercise_close)
                )
            }
        }
    }

    private fun startImageTransition(state: WorkoutState) {
        if (state.session.imageStart.isNotBlank() && state.session.imageEnd.isNotBlank()) {
            stopImageTransition()
            val sequence = arrayOf(state.session.imageStart, state.session.imageEnd)
            imageCountDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1500) {

                private var i = 0

                override fun onTick(millisUntilFinished: Long) {
                    val image = sequence[i++]
                    i %= sequence.size
                    _currentImage.postValue(image)
                }

                override fun onFinish() {}
            }.apply {
                start()
            }
        } else {
            stopImageTransition()
            _currentImage.postValue(state.session.imageStart)
        }
    }

    private fun stopImageTransition() {
        imageCountDownTimer?.cancel()
        imageCountDownTimer = null
    }

    private fun startSessionTimer(duration: Int, message: String, playBeep: Boolean = true) {
        sessionCountDownTimer?.cancel()
        sessionCountDownTimer = object : CountDownTimer(duration * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _nextState.postValue(Pair("${(millisUntilFinished / 1000) + 1}", false))
            }

            override fun onFinish() {
                _nextState.postValue(Pair(message, true))
                if (playBeep) beepPlayer.start()
            }
        }.apply {
            start()
        }
    }

    private fun stopSessionTimer() {
        sessionCountDownTimer?.cancel()
        sessionCountDownTimer = null
    }

    fun loadWorkout(workout: Workout) {
        if (_workout.value == null) {
            workoutSession = WorkoutSession(workout)
            _workout.value = workout
            nextStep()
        }
    }

    override fun onCleared() {
        super.onCleared()
        beepPlayer.stop()
    }
}