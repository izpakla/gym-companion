package rs.rocketbyte.core.model

data class Session(
    val name: String,
    val description: String,
    val image: String,
    val setCount: Int,
    val restDuration: Int,
    val repsCount: Int
)
