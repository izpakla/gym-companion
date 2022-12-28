package rs.rocketbyte.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assets(
    val multipleImages: List<String>,
) : Parcelable
