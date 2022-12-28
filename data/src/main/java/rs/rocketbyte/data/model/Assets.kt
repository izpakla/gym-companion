package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Assets(
    @SerializedName("multiple_images") val multipleImages: List<String>,
)
