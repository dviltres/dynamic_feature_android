package com.dviltres.identity_verification.ui.fphiselphi.composables

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.dviltres.identity_verification.presentation.fphiselphi.model.FphiSelphiState
import com.dviltres.identity_verification.ui.fphiselphi.FphiSelphiEvents
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.RESOURCES_PATH
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.WIDGET_CONFIGURATION
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.WIDGET_RESULT
import com.facephi.fphiwidgetcore.WidgetConfiguration
import com.facephi.fphiwidgetcore.WidgetLivenessMode
import com.facephi.fphiwidgetcore.WidgetResult
import com.facephi.selphi.Widget
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun DisplayFphiSelphiCamera(
    state: FphiSelphiState,
    events: FphiSelphiEvents
) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        val widgetResult = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activityResult.data?.getParcelableExtra(
                WIDGET_RESULT,
                WidgetResult::class.java
            )
        } else {
            activityResult.data?.getParcelableExtra(WIDGET_RESULT) as WidgetResult?
        }
        if(activityResult.resultCode == Activity.RESULT_CANCELED ||  widgetResult == null) {
            events.onNotProcessedEvent.invoke(widgetResult?.exception?.message.orEmpty())
        } else {
            events.onSuccess.invoke()
        }
    }

    val permissionState = rememberPermissionState(Manifest.permission.CAMERA) { granted->
        if(granted) {
            val conf = WidgetConfiguration().apply {
                livenessMode = WidgetLivenessMode.LIVENESS_PASSIVE
                resourcesPath = RESOURCES_PATH
                ifPhiWidgetEventListener_classname = "com.dviltres.identity_verification.ui.FPhiWidgetEventListener"
                fullscreen = true
                logImages(true)
            }
            val fphiIntent = Intent(activity, Widget::class.java)
            fphiIntent.putExtra(WIDGET_CONFIGURATION, conf)
            launcher.launch(Intent(fphiIntent))
        } else {
            events.onNotPermissionEvent.invoke()
        }
    }

    when {
        state.askForPermissionState -> {
            LaunchedEffect(Unit) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}