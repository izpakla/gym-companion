package rs.rocketbyte.data.util

import android.content.Context
import rs.rocketbyte.data.local.DefaultLocalDataSource
import rs.rocketbyte.data.local.LocalDataSource
import rs.rocketbyte.data.remote.FirebaseRemoteDataSource
import rs.rocketbyte.data.remote.RemoteDataSource
import rs.rocketbyte.data.repository.share.DefaultShareRepository
import rs.rocketbyte.data.repository.workout.DefaultWorkoutRepository

object RepositoryInjector {

    fun getDataRepositories(context: Context): DataRepositories {
        val localDataSource: LocalDataSource = DefaultLocalDataSource(context, GsonProvider.gson)
        val remoteDataSource: RemoteDataSource = FirebaseRemoteDataSource(GsonProvider.gson)

        return DataRepositories(
            DefaultWorkoutRepository(localDataSource),
            DefaultShareRepository(remoteDataSource, localDataSource)
        )
    }

}