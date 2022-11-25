package rs.rocketbyte.gym.ui.share

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.core.share.ShareUseCase
import javax.inject.Inject

@HiltViewModel
class ShareWorkoutViewModel @Inject constructor(
    private val shareUseCase: ShareUseCase
) : ViewModel() {

    fun share(workout: Workout) {
        shareUseCase.shareConfig(workout) {

        }
    }

    fun deleteShared(workout: Workout) {
        shareUseCase.deleteSharedConfig(workout) {

        }
    }

}