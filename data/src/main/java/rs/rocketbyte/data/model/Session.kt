package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("set_count") val setCount: Int,
    @SerializedName("set_duration") val setDuration: Int,
    @SerializedName("reps_count") val repsCount: Int
)
