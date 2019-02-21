@file:Suppress("unused")

package androidovshchik.project.extensions

import android.annotation.SuppressLint
import android.content.SharedPreferences

inline fun SharedPreferences.apply(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}

@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.commit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.commit()
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> apply { it.putString(key, value) }
        is Int -> apply { it.putInt(key, value) }
        is Boolean -> apply { it.putBoolean(key, value) }
        is Float -> apply { it.putFloat(key, value) }
        is Long -> apply { it.putLong(key, value) }
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: 0) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: 0f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: 0) as T?
        else -> null
    }
}