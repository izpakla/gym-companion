package rs.rocketbyte.data.local

import android.content.res.AssetManager
import com.google.gson.Gson
import rs.rocketbyte.data.model.Workout
import java.io.InputStreamReader

class DefaultLocalDataSource(private val assetManager: AssetManager) : LocalDataSource {

    companion object {
        private const val ROOT = "workouts"
        private const val CONFIG = "config.json"

        private val gson = Gson()
    }

    override suspend fun getWorkouts(): List<Workout> = getWorkoutsFromAssets()

    private fun getWorkoutsFromAssets(): List<Workout> {
        val workoutDirs = assetManager.list(ROOT)
        val workouts = ArrayList<Workout>()
        workoutDirs?.forEach {
            val inputStream = assetManager.open("$ROOT/$it/$CONFIG")
            val reader = InputStreamReader(inputStream, "UTF-8")
            val workout: Workout = gson.fromJson(reader, Workout::class.java)
            workouts.add(workout)
        }

        return workouts
    }

}