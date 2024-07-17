package com.dviltres.identity_verification.ui.fphiselphi

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.dviltres.identity_verification.presentation.FphiSelphiViewModel
import com.dviltres.identity_verification.presentation.fphiselphi.FphiSelphiUiState
import com.dviltres.identity_verification.ui.fphiselphi.composables.DisplayFphiSelphiCamera
import com.dviltres.identity_verification.ui.fphiselphi.composables.Loading

@Composable
internal fun FphiSelphiScreen(
    events: FphiSelphiEvents,
    viewModel: FphiSelphiViewModel
) {
    Scaffold(
        content = { paddingValues ->
            val fphiSelphiUiState = remember {
                viewModel.getUiState()
            }.collectAsState(initial = viewModel.defaultUiState)

            FphiSelphiContent(
                events = events,
                fphiSelphiUiState = fphiSelphiUiState,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
private fun FphiSelphiContent(
    fphiSelphiUiState: State<FphiSelphiUiState>,
    events: FphiSelphiEvents,
    paddingValues: PaddingValues
) {
    when (val currentState = fphiSelphiUiState.value) {
        is FphiSelphiUiState.SelphiUiState -> DisplayFphiSelphiCamera(
            state = currentState.state,
            events = events
        )
        FphiSelphiUiState.LoadingUiState -> Loading()
    }
}

internal data class FphiSelphiEvents(
    val onAskPermission: () -> Unit = {},
    val onSuccess: () -> Unit = {},
    val onStartFphiSelphi: () -> Unit = {},
    val onNotPermissionEvent: () -> Unit = {},
    val onNotProcessedEvent: (String) -> Unit = {}
)