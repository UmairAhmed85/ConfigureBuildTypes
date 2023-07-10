package com.changebuildtype

import android.content.Context

object BaseUrl {
    fun returnBaseUrl(context: Context): String {
        return  when (getPref(context).getInt(SharedPrefVariableNames.Configuration.value, 0)) {
            Configurations.DEBUG.value -> "https://xyz-development-api.com"
            Configurations.QA.value -> "https://xyz-qa-api.com"
            Configurations.STAGING.value -> "https://xyz-staging-api.com"
            Configurations.PROD.value -> "https://xyz-production-api.com"
            else -> {
                // Handle your exception
                throw Exception()
            }
        }
    }
}