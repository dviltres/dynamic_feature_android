package com.dviltres.identity_verification.ui.fphiselphi.composables

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.dviltres.identity_verification.presentation.fphiselphi.model.FphiSelphiState
import com.dviltres.identity_verification.ui.fphiselphi.FphiSelphiEvents
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun DisplayAskForPermission(
    state: FphiSelphiState,
    events: FphiSelphiEvents
) {
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA) { granted->
        if(granted) {
            events.onStartFphiSelphi.invoke()
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