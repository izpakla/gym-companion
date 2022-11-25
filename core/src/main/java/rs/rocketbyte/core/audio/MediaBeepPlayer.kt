package rs.rocketbyte.core.audio

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class MediaBeepPlayer(context: Context, beepRes: Int) : BeepPlayer {

    private val mediaPlayer = MediaPlayer.create(context, beepRes).apply {
        isLooping = true
        setOnPreparedListener {
            Log.d("MediaBeepPlayer", "prepared")
        }
        setOnErrorListener { mediaPlayer, i, i2 ->
            Log.d("MediaBeepPlayer", "error i=$i, i2=$i2")
            false
        }
    }

    override fun start() {
        Log.d("MediaBeepPlayer", "start called")
        if (!mediaPlayer.isPlaying) {
            Log.d("MediaBeepPlayer", "started")
            mediaPlayer.start()
        }
    }

    override fun stop() {
        if (mediaPlayer.isPlaying) {
            Log.d("MediaBeepPlayer", "stopped")
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }

    override fun dispose() {
        mediaPlayer.stop()
    }
}