package rs.rocketbyte.core.workout

import rs.rocketbyte.core.model.Exercise
import rs.rocketbyte.core.model.Session

class WorkoutSession(private val workout: Session) {

    private var ses: Int = -1
    private var set: Int = 0

    fun getNextState(): WorkoutState? {
        return when (ses) {
            -1 -> { // initial state
                WorkoutState.Ready(workout.exercises[++ses], 0)
            }
            else -> { // Session in progress
                when {
                    set >= 0 && set + 1 < workout.exercises[ses].setCount -> {
                        WorkoutState.Started(workout.exercises[ses], set++)
                    }
                    set + 1 == workout.exercises[ses].setCount -> {
                        if (ses + 1 == workout.exercises.size) {
                            set++
                            WorkoutState.FinishedWorkout(workout.exercises[workout.exercises.size - 1])
                        } else {
                            WorkoutState.LastSet(workout.exercises[ses], set++)
                        }
                    }
                    else -> {
                        set = 0
                        ses++
                        if (ses == workout.exercises.size) {
                            null
                        } else {
                            WorkoutState.Ready(workout.exercises[ses], 0)
                        }
                    }
                }
            }
        }
    }

    fun getNextExercise(): WorkoutState? {
        return when (ses) {
            -1 -> { // initial state
                WorkoutState.Ready(workout.exercises[++ses], 0)
            }
            else -> { // Session in progress
                set = 0
                ses++
                if (ses == workout.exercises.size) {
                    null
                } else {
                    WorkoutState.Ready(workout.exercises[ses], 0)
                }
            }
        }
    }
}

sealed class WorkoutState(val exercise: Exercise) {
    data class Ready(val e: Exercise, val position: Int) : WorkoutState(e) // 0
    data class Started(val e: Exercise, val position: Int) : WorkoutState(e) // 1
    data class LastSet(val e: Exercise, val position: Int) : WorkoutState(e) // N
    data class FinishedWorkout(val e: Exercise) : WorkoutState(e)
}