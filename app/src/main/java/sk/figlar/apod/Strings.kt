package sk.figlar.apod

import androidx.annotation.StringRes

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return ApplicationController.instance.getString(stringRes, *formatArgs)
    }
}