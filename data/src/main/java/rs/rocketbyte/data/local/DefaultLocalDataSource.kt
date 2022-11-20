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
            workouts.add(fixAssetPath(it, gson.fromJson(reader, Workout::class.java)))
        }

        return workouts
    }

    private fun fixAssetPath(dir: String, workout: Workout): Workout {
        val sessions =
            workout.session.map { it.copy(image = "file:///android_asset/$ROOT/$dir/${it.image}") }
        return workout.copy(session = sessions)
    }

}