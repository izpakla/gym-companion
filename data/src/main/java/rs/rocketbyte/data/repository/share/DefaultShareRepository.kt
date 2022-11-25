package rs.rocketbyte.data.repository.share

import rs.rocketbyte.data.model.Workout
import rs.rocketbyte.data.remote.RemoteDataSource

class DefaultShareRepository(private val remoteDataSource: RemoteDataSource) : ShareRepository {

    override fun uploadConfig(workout: Workout, onComplete: (String?) -> Unit) =
        remoteDataSource.uploadConfig(workout, onComplete)

    override fun deleteConfig(workout: Workout, onComplete: (Boolean) -> Unit) =
        remoteDataSource.deleteConfig(workout, onComplete)

}