package rs.rocketbyte.core.util

import android.content.Context
import rs.rocketbyte.core.usecase.workout.DefaultWorkoutUseCase
import rs.rocketbyte.core.usecase.workout.WorkoutUseCase
import rs.rocketbyte.data.util.RepositoryInjector

object UseCaseInjector {

    fun getDefaultExampleUseCase(context: Context): WorkoutUseCase =
        DefaultWorkoutUseCase(RepositoryInjector.getDefaultExampleRepository(context))

}