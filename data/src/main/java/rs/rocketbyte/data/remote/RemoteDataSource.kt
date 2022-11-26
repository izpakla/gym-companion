package rs.rocketbyte.data.remote

import rs.rocketbyte.data.model.Workout

interface RemoteDataSource {
    fun uploadConfig(deviceId: String, workout: Workout, onComplete: (String?) -> Unit)
    fun deleteConfig(deviceId: String, workout: Workout, onComplete: (Boolean) -> Unit)
}