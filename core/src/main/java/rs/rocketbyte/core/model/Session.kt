package rs.rocketbyte.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val name: String,
    val exercises: List<Exercise>
) : Parcelable
