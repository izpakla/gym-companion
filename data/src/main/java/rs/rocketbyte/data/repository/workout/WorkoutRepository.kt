package rs.rocketbyte.data.repository.workout

import rs.rocketbyte.data.model.Workout

interface WorkoutRepository {

    suspend fun getWorkouts(): List<Workout>

}