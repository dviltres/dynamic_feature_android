package com.dviltres.identity_verification.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dviltres.identity_verification.presentation.FphiSelphiViewModel
import com.dviltres.identity_verification.ui.fphiselphi.FphiSelphiEvents
import com.dviltres.identity_verification.ui.fphiselphi.FphiSelphiScreen

internal fun NavGraphBuilder.fphiSelphiNav(
    viewModel: FphiSelphiViewModel,
    onSuccess: () -> Unit = {},
    onNotPermissionEvent: () -> Unit = {},
    onNotProcessedEvent: (String) -> Unit = {}
) = composable(
    route = IdentityVerificationRoutes.FphiSelphi.path
) {
    val initScreen = remember { mutableStateOf(true) }
    if (initScreen.value) {
        initScreen.value = false
        viewModel.startSelphi()
    }
    FphiSelphiScreen(
        events = FphiSelphiEvents(
            onStartFphiSelphi = {
                viewModel.startSelphi()
            },
            onSuccess = onSuccess,
            onNotPermissionEvent = onNotPermissionEvent,
            onNotProcessedEvent = onNotProcessedEvent
        ),
        viewModel = viewModel
    )
}
