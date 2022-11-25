package rs.rocketbyte.core.model.mapper

import rs.rocketbyte.data.model.Session
import rs.rocketbyte.data.model.Workout

fun Workout.map(): rs.rocketbyte.core.model.Workout {
    return rs.rocketbyte.core.model.Workout(
        name = name,
        description = description,
        coverImage = coverImage,
        session = session.map { it.map() }
    )
}

fun Session.map(): rs.rocketbyte.core.model.Session {
    return rs.rocketbyte.core.model.Session(
        name = name,
        description = description,
        muscleTargeted = muscleTargeted,
        imageStart = imageStart,
        imageEnd = imageEnd,
        setCount = setCount,
        setDuration = setDuration,
        repsCount = repsCount
    )
}

fun rs.rocketbyte.core.model.Workout.map(): Workout {
    return Workout(
        name = name,
        description = description,
        coverImage = coverImage,
        session = session.map { it.map() }
    )
}

fun rs.rocketbyte.core.model.Session.map(): Session {
    return Session(
        name = name,
        description = description,
        muscleTargeted = muscleTargeted,
        imageStart = imageStart,
        imageEnd = imageEnd,
        setCount = setCount,
        setDuration = setDuration,
        repsCount = repsCount
    )
}