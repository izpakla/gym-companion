package rs.rocketbyte.data.remote

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import rs.rocketbyte.data.model.Workout

class FirebaseRemoteDataSource(private val gson: Gson = Gson()) : RemoteDataSource {

    private val storage = Firebase.storage("gs://gym-app-39118.appspot.com/")
    private var storageRef = storage.reference

    override fun uploadConfig(deviceId: String, workout: Workout, onComplete: (String?) -> Unit) {
        val configRef = storageRef.child(getShareFileRef(deviceId, workout))
        configRef.updateMetadata(StorageMetadata())
        val uploadTask = configRef.putBytes(gson.toJson(workout).encodeToByteArray())
        uploadTask.addOnFailureListener {
            onComplete(null)
        }
        uploadTask.addOnSuccessListener { _ ->
            configRef.downloadUrl.apply {
                addOnFailureListener {
                    onComplete(null)
                }
                addOnSuccessListener { uri ->
                    onComplete(uri.toString())
                }
            }
        }
    }

    override fun deleteConfig(deviceId: String, workout: Workout, onComplete: (Boolean) -> Unit) {
        val configRef = storageRef.child(getShareFileRef(deviceId, workout))
        val deleteTask = configRef.delete()
        deleteTask.addOnFailureListener {
            onComplete(false)
        }
        deleteTask.addOnSuccessListener {
            onComplete(true)
        }
    }

    private fun getShareFileRef(deviceId: String, workout: Workout) =
        "$deviceId/${workout.name.replace(' ', '-')}.json"

}