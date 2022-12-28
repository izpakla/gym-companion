package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("muscle_targeted") val muscleTargeted: String,
    @SerializedName("assets") val assets: Assets,
    @SerializedName("set_count") val setCount: Int,
    @SerializedName("set_duration") val setDuration: Int,
    @SerializedName("reps_count") val repsCount: Int
)
