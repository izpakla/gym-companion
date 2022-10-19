package rs.rocketbyte.core.util

import android.content.Context
import rs.rocketbyte.data.util.RepositoryInjector

object UseCaseInjector {

    fun getDefaultExampleUseCase(context: Context): rs.rocketbyte.core.usecase.example.WorkoutUseCase {
        return rs.rocketbyte.core.usecase.example.DefaultWorkoutUseCase(
            RepositoryInjector.getDefaultExampleRepository(
                context
            )
        )
    }

}