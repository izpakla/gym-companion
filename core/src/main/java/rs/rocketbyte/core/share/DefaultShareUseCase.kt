package rs.rocketbyte.core.share

import android.graphics.Bitmap
import android.util.Log
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.model.mapper.map
import rs.rocketbyte.data.repository.share.ShareRepository

class DefaultShareUseCase(private val shareRepository: ShareRepository) : ShareUseCase {

    override fun shareConfig(workout: Workout, onComplete: (Bitmap) -> Unit) {
        shareRepository.uploadConfig(workout.map()) {
            Log.d("DefaultShareUseCase", "URL: $it")
        }
    }

    override fun deleteSharedConfig(workout: Workout, onComplete: (Boolean) -> Unit) {
        shareRepository.deleteConfig(workout.map()) {
            Log.d("DefaultShareUseCase", "Delete success: $it")
        }
    }

}