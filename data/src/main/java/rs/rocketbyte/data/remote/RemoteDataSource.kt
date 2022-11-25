package rs.rocketbyte.data.remote

import rs.rocketbyte.data.model.Workout

interface RemoteDataSource {
    fun uploadConfig(workout: Workout, onComplete: (String?) -> Unit)
    fun deleteConfig(workout: Workout, onComplete: (Boolean) -> Unit)
}