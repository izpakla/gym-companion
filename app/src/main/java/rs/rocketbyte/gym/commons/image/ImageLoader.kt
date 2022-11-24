package rs.rocketbyte.gym.commons.image

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoader {

    fun load(context: Context, image: String, imageView: ImageView) {
        Glide.with(context)
            .load(Uri.parse(image))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }

}
