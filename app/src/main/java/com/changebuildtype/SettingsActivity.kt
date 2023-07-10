package com.changebuildtype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.changebuildtype.databinding.SettingsActivityBinding
import com.google.android.material.snackbar.Snackbar


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: SettingsActivityBinding
    private var versionClickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.settings_activity)

        var currentEnviroment = ""
        when (getPref(this).getInt(SharedPrefVariableNames.Environment.value, 0)) {
            EnvironmentType.DEBUG.value -> {
                currentEnviroment = "DEBUG"
            }
            EnvironmentType.QA.value -> {
                currentEnviroment = "QA"
            }
            EnvironmentType.STAGING.value -> {
                currentEnviroment = "STAGING"
            }
            EnvironmentType.PROD.value -> {
                currentEnviroment = "PRODUCTION"
            }
        }

        binding.currentBuildType.text = binding.currentBuildType.text.toString().plus(currentEnviroment)
        binding.currentBaseUrl.text = binding.currentBaseUrl.text.toString().plus(BaseUrl.returnBaseUrl(this))
        /*if (EnvironmentType.PROD.value == getPref(this).getInt(SharedPrefVariableNames.Environment.value, 0)) {
            binding.thirdPartyLibrary.text = binding.thirdPartyLibrary.text.toString().plus(getString(R.string.THIRD_PARTY_LIBRARY))
            binding.thirdPartyLibrary.visibility = View.VISIBLE
        }*/

        binding.version.setOnClickListener {
            if (++versionClickCount >= 5) {
                val msg = when (getPref(this).getInt(SharedPrefVariableNames.Environment.value, 0)) {
                    EnvironmentType.DEBUG.value -> "Initialize QA Environment settings?"
                    EnvironmentType.QA.value -> "Initialize Staging Environment settings?"
                    EnvironmentType.STAGING.value -> "Initialize Production Environment settings?"
                    EnvironmentType.PROD.value -> "Initialize Development Environment settings?"
                    else -> "Initialize Development Environment settings?"
                }
                val snack = Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
                snack.setTextColor(ContextCompat.getColor(this, R.color.white))
                snack.setActionTextColor(ContextCompat.getColor(this, R.color.yellow))
                snack.setAction("CHANGE") {
                    getPref(this).edit().putInt(
                        SharedPrefVariableNames.Environment.value,
                        (getPref(this).getInt(SharedPrefVariableNames.Environment.value, 0) + 1) % 4
                    ).commit()
                    restartActivity(intent, this)
                }
                snack.show()
            }
        }
    }
}