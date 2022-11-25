package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Workout(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("cover_image") val coverImage: String,
    @SerializedName("session") val session: List<Session>
)
