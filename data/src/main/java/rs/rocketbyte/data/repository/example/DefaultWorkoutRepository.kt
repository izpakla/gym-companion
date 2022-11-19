package rs.rocketbyte.data.repository.example

import rs.rocketbyte.data.local.LocalDataSource
import rs.rocketbyte.data.model.Workout
import rs.rocketbyte.data.remote.RemoteDataSource

internal class DefaultWorkoutRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WorkoutRepository {

    override suspend fun getWorkouts(): List<Workout> {
        return localDataSource.getWorkouts()
    }

}