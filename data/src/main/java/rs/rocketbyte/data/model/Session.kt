package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("muscle_targeted") val muscleTargeted: String,
    @SerializedName("image_start") val imageStart: String,
    @SerializedName("image_end") val imageEnd: String,
    @SerializedName("set_count") val setCount: Int,
    @SerializedName("set_duration") val setDuration: Int,
    @SerializedName("reps_count") val repsCount: Int
)
