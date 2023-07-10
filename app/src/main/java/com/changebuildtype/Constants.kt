package com.changebuildtype

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

private const val MY_SHARED_PREFERENCE_NAME = "shared_preferences"
fun getPref(context: Context): SharedPreferences {
    return context.getSharedPreferences(MY_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
}

enum class SharedPrefVariableNames(val value: String) {
    Configuration("environment")
}

enum class Configurations(val value: Int) {
    DEBUG(0),
    QA(1),
    STAGING(2),
    PROD(3)
}

fun restartActivity(intent: Intent, activity: SettingsActivity){
    activity.finish()
    activity.startActivity(intent)

}