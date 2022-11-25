package rs.rocketbyte.core.share

import android.graphics.Bitmap
import rs.rocketbyte.core.model.Workout

interface ShareUseCase {
    fun shareConfig(workout: Workout, onComplete: (Bitmap) -> Unit)
    fun deleteSharedConfig(workout: Workout, onComplete: (Boolean) -> Unit)
}