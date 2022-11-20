package rs.rocketbyte.core.workout

import rs.rocketbyte.core.model.Session
import rs.rocketbyte.core.model.Workout

class WorkoutSession(private val workout: Workout) {

    private var currentSession: Session = workout.session.first()
    private var sessionPosition = 0
    private var sessionSet = 0

    private fun hasMoreSets(): Boolean = currentSession.setCount > sessionSet
    private fun hasMoreSessions(): Boolean = workout.session.size > sessionPosition

    fun getCurrentState(): Pair<Session, Int>? =
        if (hasMoreSessions() || hasMoreSets()) Pair(currentSession, sessionSet) else null

    fun next(): Pair<Session, Int>? = if (hasMoreSets()) {
        Pair(currentSession, sessionSet++)
    } else {
        val session = workout.session.getOrNull(++sessionPosition)
        if (session != null) {
            currentSession = session
            sessionSet = 0
            Pair(currentSession, sessionSet++)
        } else {
            null
        }
    }

}