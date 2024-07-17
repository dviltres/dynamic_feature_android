package com.dviltres.identity_verification.presentation.fphiselphi.model

internal data class FphiSelphiState(
    val askForPermissionState: Boolean = true
)

internal fun FphiSelphiState.askForPermission(askForPermission: Boolean = true) = this.copy(
    askForPermissionState = askForPermission
)
