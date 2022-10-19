package rs.rocketbyte.core.usecase.example

import rs.rocketbyte.data.repository.example.WorkoutRepository

internal class DefaultWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) : WorkoutUseCase {

}