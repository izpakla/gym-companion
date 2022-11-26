package rs.rocketbyte.core.util

import android.content.Context
import rs.rocketbyte.core.share.DefaultShareUseCase
import rs.rocketbyte.core.share.ShareUseCase
import rs.rocketbyte.core.workout.DefaultWorkoutUseCase
import rs.rocketbyte.core.workout.WorkoutUseCase
import rs.rocketbyte.data.util.RepositoryInjector

object UseCaseInjector {

    fun getDomainUseCases(context: Context): DomainUseCases {
        val dataRepositories = RepositoryInjector.getDataRepositories(context)
        val workoutUseCase: WorkoutUseCase =
            DefaultWorkoutUseCase(dataRepositories.workoutRepository)
        val shareUseCase: ShareUseCase = DefaultShareUseCase(dataRepositories.shareRepository)
        return DomainUseCases(workoutUseCase, shareUseCase)
    }

}