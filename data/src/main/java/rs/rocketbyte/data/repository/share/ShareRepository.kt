package rs.rocketbyte.data.repository.share

import rs.rocketbyte.data.model.Workout

interface ShareRepository {

    fun uploadConfig(workout: Workout, onComplete: (String?) -> Unit)

    fun deleteConfig(workout: Workout, onComplete: (Boolean) -> Unit)

}