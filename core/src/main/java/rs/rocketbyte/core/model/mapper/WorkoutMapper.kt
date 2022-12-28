package rs.rocketbyte.core.model.mapper

import rs.rocketbyte.data.model.Assets
import rs.rocketbyte.data.model.Exercise
import rs.rocketbyte.data.model.Session
import rs.rocketbyte.data.model.Workout

/**
 * From Data
 */

fun Workout.map(): rs.rocketbyte.core.model.Workout {
    return rs.rocketbyte.core.model.Workout(
        name = name,
        description = description,
        coverImage = coverImage,
        sessions = sessions.map { it.map() }
    )
}

fun Session.map(): rs.rocketbyte.core.model.Session {
    return rs.rocketbyte.core.model.Session(
        name = name,
        exercises = exercises.map { it.map() }
    )
}

fun Exercise.map(): rs.rocketbyte.core.model.Exercise {
    return rs.rocketbyte.core.model.Exercise(
        name = name,
        description = description,
        muscleTargeted = muscleTargeted,
        assets = assets.map(),
        setCount = setCount,
        setDuration = setDuration,
        repsCount = repsCount
    )
}

fun Assets.map(): rs.rocketbyte.core.model.Assets {
    return rs.rocketbyte.core.model.Assets(
        multipleImages = ArrayList(multipleImages)
    )
}

/**
 * To Data
 */

fun rs.rocketbyte.core.model.Workout.map(): Workout {
    return Workout(
        name = name,
        description = description,
        coverImage = coverImage,
        sessions = sessions.map { it.map() }
    )
}

fun rs.rocketbyte.core.model.Session.map(): Session {
    return Session(
        name = name,
        exercises = exercises.map { it.map() }
    )
}

fun rs.rocketbyte.core.model.Exercise.map(): Exercise {
    return Exercise(
        name = name,
        description = description,
        muscleTargeted = muscleTargeted,
        assets = assets.map(),
        setCount = setCount,
        setDuration = setDuration,
        repsCount = repsCount
    )
}

fun rs.rocketbyte.core.model.Assets.map(): Assets {
    return Assets(
        multipleImages = ArrayList(multipleImages)
    )
}

