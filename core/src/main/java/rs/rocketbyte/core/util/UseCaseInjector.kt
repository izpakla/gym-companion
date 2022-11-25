package rs.rocketbyte.core.util

import android.content.Context
import rs.rocketbyte.core.share.DefaultShareUseCase
import rs.rocketbyte.core.share.ShareUseCase
import rs.rocketbyte.core.workout.DefaultWorkoutUseCase
import rs.rocketbyte.core.workout.WorkoutUseCase
import rs.rocketbyte.data.util.RepositoryInjector

object UseCaseInjector {

    fun getWorkoutUseCase(context: Context): WorkoutUseCase =
        DefaultWorkoutUseCase(RepositoryInjector.getWorkoutRepository(context))

    fun getShareUseCase(): ShareUseCase =
        DefaultShareUseCase(RepositoryInjector.getShareRepository())

}