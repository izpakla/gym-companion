package rs.rocketbyte.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Workout(
    val name: String,
    val description: String,
    val coverImage: String,
    val sessions: List<Session>
) : Parcelable
