package rs.rocketbyte.data.repository.workout

import rs.rocketbyte.data.local.LocalDataSource
import rs.rocketbyte.data.model.Workout

internal class DefaultWorkoutRepository(private val localDataSource: LocalDataSource) :
    WorkoutRepository {

    override suspend fun getWorkouts(): List<Workout> = localDataSource.getWorkouts()

}