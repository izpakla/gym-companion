package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("name") val name: String,
    @SerializedName("exercises") val exercises: List<Exercise>
)
