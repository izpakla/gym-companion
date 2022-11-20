package rs.rocketbyte.core.workout

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout


class WorkoutSessionUnitTest {
    @Test
    fun sessionForward_isCorrect() {
        val workoutSession = WorkoutSession(createDemoWorkout())
        var a1 = workoutSession.next()
        assertNotNull(a1)
        assertEquals("test1", a1?.first?.name)
        assertEquals(0, a1?.second)
        a1 = workoutSession.next()
        assertEquals(1, a1?.second)
        a1 = workoutSession.next()
        assertEquals(2, a1?.second)

        val a2 = workoutSession.next()
        assertNotNull(a2)
        assertEquals("test2", a2?.first?.name)
        assertEquals(0, a2?.second)
    }

    @Test
    fun sessionForwardSetCount_isCorrect() {
        val workoutSession = WorkoutSession(createDemoWorkout())

        repeat(3) {
            val pair = workoutSession.next()!!
            assertEquals("test1", pair.first.name)
            assertEquals(it, pair.second)
        }
        repeat(5) {
            val pair = workoutSession.next()!!
            assertEquals("test2", pair.first.name)
            assertEquals(it, pair.second)
        }
        repeat(1) {
            val pair = workoutSession.next()!!
            assertEquals("test3", pair.first.name)
            assertEquals(it, pair.second)
        }
    }

}

private fun createDemoWorkout(): Workout {
    val sessions = arrayListOf(
        Session(
            name = "test1",
            description = "testd1",
            image = "",
            setCount = 3,
            restDuration = 100,
            repsCount = 10
        ),
        Session(
            name = "test2",
            description = "testd2",
            image = "",
            setCount = 5,
            restDuration = 100,
            repsCount = 10
        ),
        Session(
            name = "test3",
            description = "testd3",
            image = "",
            setCount = 1,
            restDuration = 100,
            repsCount = 10
        )
    )
    return Workout("workout", sessions)
}