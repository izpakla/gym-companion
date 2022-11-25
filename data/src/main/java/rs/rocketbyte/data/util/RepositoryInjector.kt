package rs.rocketbyte.data.util

import android.content.Context
import rs.rocketbyte.data.local.DefaultLocalDataSource
import rs.rocketbyte.data.remote.FirebaseRemoteDataSource
import rs.rocketbyte.data.repository.share.DefaultShareRepository
import rs.rocketbyte.data.repository.share.ShareRepository
import rs.rocketbyte.data.repository.workout.DefaultWorkoutRepository
import rs.rocketbyte.data.repository.workout.WorkoutRepository

object RepositoryInjector {

    fun getWorkoutRepository(
        context: Context
    ): WorkoutRepository = DefaultWorkoutRepository(
        DefaultLocalDataSource(context.assets, GsonProvider.gson)
    )

    fun getShareRepository(): ShareRepository = DefaultShareRepository(
        FirebaseRemoteDataSource(GsonProvider.gson)
    )

}