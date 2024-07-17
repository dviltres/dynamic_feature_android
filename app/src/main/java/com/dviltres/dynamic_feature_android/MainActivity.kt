package com.dviltres.dynamic_feature_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import com.dviltres.dynamic_feature_android.components.DownloadModuleConfirmationDialog
import com.dviltres.dynamic_feature_android.dynamic_module.DynamicDeliveryCallback
import com.dviltres.dynamic_feature_android.dynamic_module.DynamicModuleDownloadUtil
import com.dviltres.dynamic_feature_android.ui.theme.DynamicFeatureTheme
import java.util.Calendar

class MainActivity : ComponentActivity(), DynamicDeliveryCallback {
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil
    private var logState = mutableStateOf("Activity Log:\n")
    private var dialogState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(baseContext, this)

        setContent {
            DynamicFeatureTheme {
                Surface {
                    DemoScreen(
                        logState = logState.value,
                        onClickDefaultUI = {
                            openCustomerSupportFeature()
                        }
                    )
                    DownloadModuleConfirmationDialog(dialogState, ::downloadDynamicModule)
                }
            }
        }
    }

    private fun openCustomerSupportFeature() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded(DYNAMIC_MODULE)) {
            logState.value += "${getCurrentTimestamp()}: Module is already downloaded.\n"
            startIdentityVerificationActivity()
        } else {
            dialogState.value = true
        }
    }

    private fun startIdentityVerificationActivity() {
        val intent = Intent()
        intent.setClassName(
            APP_PACKAGE_NAME,
            CLASS_NAME
        )
        startActivity(intent)
    }

    private fun downloadDynamicModule() {
        logState.value += "${getCurrentTimestamp()}: Call for download.\n"
        dynamicModuleDownloadUtil.downloadDynamicModule(DYNAMIC_MODULE)
    }

    override fun onDownloading() {
        logState.value += "${getCurrentTimestamp()}: Downloading...\n"
    }

    override fun onDownloadCompleted() {
        logState.value += "${getCurrentTimestamp()}: Module download completed.\n"
    }

    override fun onInstallSuccess() {
        logState.value += "${getCurrentTimestamp()}: Module install Success!\n"
        startIdentityVerificationActivity()
    }

    override fun onFailed(errorMessage: String) {
        logState.value += "${getCurrentTimestamp()}: $errorMessage\n"
    }

    private fun getCurrentTimestamp(): String {
        val calendar = Calendar.getInstance()
        return "${calendar.get(Calendar.HOUR).toString().padStart(2, '0')}:" +
                "${calendar.get(Calendar.MINUTE).toString().padStart(2, '0')}:" +
                calendar.get(Calendar.SECOND).toString().padStart(2, '0')
    }

    companion object {
        const val DYNAMIC_MODULE = "identity_verification"
        const val APP_PACKAGE_NAME = "com.dviltres.dynamic_feature_android"
        const val CLASS_NAME = "com.dviltres.identity_verification.ui.IdentityVerificationActivity"
    }
}
