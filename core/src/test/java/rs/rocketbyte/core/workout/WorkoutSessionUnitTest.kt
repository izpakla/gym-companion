package rs.rocketbyte.core.workout

import org.junit.Assert.*
import org.junit.Test
import rs.rocketbyte.core.model.Assets
import rs.rocketbyte.core.model.Exercise
import rs.rocketbyte.core.model.Session


class WorkoutSessionUnitTest {

    @Test
    fun getNextState_isCorrect() {
        val workoutSession = WorkoutSession(createDemoWorkout())

        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(0, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(1, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.LastSet)
            assertEquals(2, (it as WorkoutState.LastSet).position)
        }

        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(0, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(1, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(2, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Started)
            assertEquals(3, (it as WorkoutState.Started).position)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.LastSet)
            assertEquals(4, (it as WorkoutState.LastSet).position)
        }

        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.FinishedWorkout)
        }
        assertNull(workoutSession.getNextState())
    }

    @Test
    fun getNextSession_isCorrect() {
        val workoutSession = WorkoutSession(createDemoWorkout())

        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextExercise().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextExercise().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.FinishedWorkout)
        }
        assertNull(workoutSession.getNextExercise())
    }

}

private fun createDemoWorkout(): Session {
    val exercises = arrayListOf(
        Exercise(
            name = "test1",
            description = "testd1",
            muscleTargeted = "",
            assets = Assets(emptyList()),
            setCount = 3,
            setDuration = 100,
            repsCount = 10
        ),
        Exercise(
            name = "test2",
            description = "testd2",
            muscleTargeted = "",
            assets = Assets(emptyList()),
            setCount = 5,
            setDuration = 100,
            repsCount = 10
        ),
        Exercise(
            name = "test3",
            description = "testd3",
            muscleTargeted = "",
            assets = Assets(emptyList()),
            setCount = 1,
            setDuration = 100,
            repsCount = 10
        )
    )
    return Session("Monday", exercises)
}