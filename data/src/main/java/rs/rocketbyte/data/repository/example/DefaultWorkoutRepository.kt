package rs.rocketbyte.data.repository.example

import rs.rocketbyte.data.local.LocalDataSource
import rs.rocketbyte.data.remote.RemoteDataSource

internal class DefaultWorkoutRepository(
    private val apiDataSource: RemoteDataSource,
    private val dbDataSource: LocalDataSource
) : WorkoutRepository {

}