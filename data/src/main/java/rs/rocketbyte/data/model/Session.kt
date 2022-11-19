package rs.rocketbyte.data.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("image") var image: String,
    @SerializedName("set_count") var setCount: Int,
    @SerializedName("rest_duration") var restDuration: Int,
    @SerializedName("reps_count") var repsCount: Int
)
