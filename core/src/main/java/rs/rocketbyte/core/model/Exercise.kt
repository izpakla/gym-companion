package rs.rocketbyte.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val name: String,
    val description: String,
    val muscleTargeted: String,
    val assets: Assets,
    val setCount: Int,
    val setDuration: Int,
    val repsCount: Int
) : Parcelable
