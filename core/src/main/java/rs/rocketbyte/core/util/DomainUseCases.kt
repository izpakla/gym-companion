package rs.rocketbyte.core.util

import rs.rocketbyte.core.share.ShareUseCase
import rs.rocketbyte.core.workout.WorkoutUseCase

data class DomainUseCases(
    val workoutUseCase: WorkoutUseCase,
    val shareUseCase: ShareUseCase
)
