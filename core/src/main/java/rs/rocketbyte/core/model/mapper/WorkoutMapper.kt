package rs.rocketbyte.core.model.mapper

import rs.rocketbyte.data.model.Session
import rs.rocketbyte.data.model.Workout

fun Workout.map(): rs.rocketbyte.core.model.Workout {
    return rs.rocketbyte.core.model.Workout(
        name = name,
        description = description,
        session = session.map { it.map() }
    )
}

fun Session.map(): rs.rocketbyte.core.model.Session {
    return rs.rocketbyte.core.model.Session(
        name = name,
        description = description,
        image = image,
        setCount = setCount,
        setDuration = setDuration,
        repsCount = repsCount
    )
}