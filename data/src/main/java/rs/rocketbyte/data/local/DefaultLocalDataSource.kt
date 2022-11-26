package rs.rocketbyte.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.google.gson.Gson
import rs.rocketbyte.data.model.Workout
import java.io.InputStreamReader
import java.util.*

class DefaultLocalDataSource(context: Context, private val gson: Gson) :
    LocalDataSource {

    companion object {
        private const val ROOT = "workouts"
        private const val CONFIG = "config.json"

        private const val DEVICE_ID = "device_id"
    }

    private val assetManager: AssetManager = context.assets

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("${context.packageName}.local", MODE_PRIVATE)

    override suspend fun getWorkouts(): List<Workout> = getWorkoutsFromAssets()

    override fun getDeviceId(): String {
        val stored = sharedPreferences.getString(DEVICE_ID, null)
        return if (stored == null) {
            val id = UUID.randomUUID().toString()
            sharedPreferences.edit().putString(DEVICE_ID, id).apply()
            id
        } else {
            stored
        }
    }

    private fun getWorkoutsFromAssets(): List<Workout> {
        val workoutDirs = assetManager.list(ROOT)
        val workouts = ArrayList<Workout>()
        workoutDirs?.forEach {
            val inputStream = assetManager.open("$ROOT/$it/$CONFIG")
            val reader = InputStreamReader(inputStream, "UTF-8")
            workouts.add(gson.fromJson(reader, Workout::class.java))
        }

        return workouts
    }

}