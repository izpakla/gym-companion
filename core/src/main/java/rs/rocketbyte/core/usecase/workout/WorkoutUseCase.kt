package rs.rocketbyte.core.usecase.workout

import rs.rocketbyte.core.model.Workout


interface WorkoutUseCase {
    suspend fun getAllWorkouts(): List<Workout>
}