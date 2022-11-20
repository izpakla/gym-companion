package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Workout(
    @SerializedName("name") val name: String,
    @SerializedName("session") val session: List<Session>
)
