package rs.rocketbyte.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val name: String,
    val description: String,
    val image: String,
    val setCount: Int,
    val setDuration: Int,
    val repsCount: Int
) : Parcelable
