package rs.rocketbyte.core.workout

import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout

class WorkoutSession(private val workout: Workout) {

    private var ses: Int = -1
    private var set: Int = 0

    fun getNextState(): WorkoutState? {
        return when (ses) {
            -1 -> { // initial state
                WorkoutState.Ready(workout.session[++ses], 0)
            }
            else -> { // Session in progress
                when {
                    set >= 0 && set + 1 < workout.session[ses].setCount -> {
                        WorkoutState.Started(workout.session[ses], set++)
                    }
                    set + 1 == workout.session[ses].setCount -> {
                        if (ses + 1 == workout.session.size) {
                            set++
                            WorkoutState.FinishedWorkout(workout.session[workout.session.size - 1])
                        } else {
                            WorkoutState.LastSet(workout.session[ses], set++)
                        }
                    }
                    else -> {
                        set = 0
                        ses++
                        if (ses == workout.session.size) {
                            null
                        } else {
                            WorkoutState.Ready(workout.session[ses], 0)
                        }
                    }
                }
            }
        }
    }

    fun getNextSession(): WorkoutState? {
        return when (ses) {
            -1 -> { // initial state
                WorkoutState.Ready(workout.session[++ses], 0)
            }
            else -> { // Session in progress
                set = 0
                ses++
                if (ses == workout.session.size) {
                    null
                } else {
                    WorkoutState.Ready(workout.session[ses], 0)
                }
            }
        }
    }
}

sealed class WorkoutState(val session: Session) {
    data class Ready(val s: Session, val position: Int) : WorkoutState(s) // 0
    data class Started(val s: Session, val position: Int) : WorkoutState(s) // 1
    data class LastSet(val s: Session, val position: Int) : WorkoutState(s) // N
    data class FinishedWorkout(val s: Session) : WorkoutState(s)
}