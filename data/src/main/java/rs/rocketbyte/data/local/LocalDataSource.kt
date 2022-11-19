package rs.rocketbyte.data.local

import rs.rocketbyte.data.model.Workout

interface LocalDataSource {

    suspend fun getWorkouts(): List<Workout>

}