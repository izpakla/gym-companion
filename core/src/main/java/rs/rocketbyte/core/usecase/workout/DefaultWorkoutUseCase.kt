package rs.rocketbyte.core.usecase.workout

import rs.rocketbyte.data.model.Workout
import rs.rocketbyte.data.repository.example.WorkoutRepository

internal class DefaultWorkoutUseCase(private val workoutRepository: WorkoutRepository) :
    WorkoutUseCase {

    override suspend fun getAllWorkouts(): List<Workout> = workoutRepository.getWorkouts()

}