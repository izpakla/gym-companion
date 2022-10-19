package rs.rocketbyte.data.util

import android.content.Context
import rs.rocketbyte.data.local.DefaultLocalDataSource
import rs.rocketbyte.data.local.db.AppDatabase
import rs.rocketbyte.data.remote.DefaultRemoteDataSource
import rs.rocketbyte.data.remote.api.RestApiConfig
import rs.rocketbyte.data.repository.example.DefaultWorkoutRepository
import rs.rocketbyte.data.repository.example.WorkoutRepository

object RepositoryInjector {

    fun getDefaultExampleRepository(
        context: Context,
        restApiConfig: RestApiConfig = RestApiConfig.loadConfig(context)
    ): WorkoutRepository = DefaultWorkoutRepository(
        DefaultRemoteDataSource(restApiConfig),
        DefaultLocalDataSource(
            AppDatabase.getInstance(
                context
            )
        )
    )

}