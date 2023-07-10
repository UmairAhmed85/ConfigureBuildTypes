package com.changebuildtype

import android.content.Context

object BaseUrl {
    fun returnBaseUrl(context: Context): String {
        return  when (getPref(context).getInt(SharedPrefVariableNames.Environment.value, 0)) {
            EnvironmentType.DEBUG.value -> "https://xyz-development-api.com"
            EnvironmentType.QA.value -> "https://xyz-qa-api.com"
            EnvironmentType.STAGING.value -> "https://xyz-staging-api.com"
            EnvironmentType.PROD.value -> "https://xyz-production-api.com"
            else -> {
                // Handle your exception
                throw Exception()
            }
        }
    }
}