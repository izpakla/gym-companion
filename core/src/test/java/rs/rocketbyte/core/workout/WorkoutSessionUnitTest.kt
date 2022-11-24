package rs.rocketbyte.core.workout

import org.junit.Assert.*
import org.junit.Test
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout


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
        workoutSession.getNextSession().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextSession().let {
            assertTrue(it is WorkoutState.Ready)
        }
        workoutSession.getNextState().let {
            assertTrue(it is WorkoutState.FinishedWorkout)
        }
        assertNull(workoutSession.getNextSession())
    }

}

private fun createDemoWorkout(): Workout {
    val sessions = arrayListOf(
        Session(
            name = "test1",
            description = "testd1",
            image = "",
            setCount = 3,
            setDuration = 100,
            repsCount = 10
        ),
        Session(
            name = "test2",
            description = "testd2",
            image = "",
            setCount = 5,
            setDuration = 100,
            repsCount = 10
        ),
        Session(
            name = "test3",
            description = "testd3",
            image = "",
            setCount = 1,
            setDuration = 100,
            repsCount = 10
        )
    )
    return Workout("workout", "description", sessions)
}