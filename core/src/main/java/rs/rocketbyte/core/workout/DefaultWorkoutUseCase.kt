package rs.rocketbyte.core.workout

import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.model.mapper.map
import rs.rocketbyte.data.repository.workout.WorkoutRepository

internal class DefaultWorkoutUseCase(private val workoutRepository: WorkoutRepository) :
    WorkoutUseCase {

    override suspend fun getAllWorkouts(): List<Workout> =
        workoutRepository.getWorkouts().map { it.map() }

}