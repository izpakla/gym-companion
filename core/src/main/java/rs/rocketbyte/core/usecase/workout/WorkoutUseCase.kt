package rs.rocketbyte.core.usecase.workout

import rs.rocketbyte.data.model.Workout

interface WorkoutUseCase {
    suspend fun getAllWorkouts(): List<Workout>
}