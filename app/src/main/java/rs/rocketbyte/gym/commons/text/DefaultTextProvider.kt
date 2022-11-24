package rs.rocketbyte.gym.commons.text

import android.content.Context

class DefaultTextProvider(context: Context) : TextProvider {
    private val appContext = context.applicationContext

    override fun getText(resId: Int): String = appContext.getString(resId)
}