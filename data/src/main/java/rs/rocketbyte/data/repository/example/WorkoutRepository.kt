package rs.rocketbyte.data.repository.example

import rs.rocketbyte.data.model.Workout

interface WorkoutRepository {

    suspend fun getWorkouts(): List<Workout>

}