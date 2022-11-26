package rs.rocketbyte.data.util

import rs.rocketbyte.data.repository.share.ShareRepository
import rs.rocketbyte.data.repository.workout.WorkoutRepository

data class DataRepositories(
    val workoutRepository: WorkoutRepository,
    val shareRepository: ShareRepository
)
