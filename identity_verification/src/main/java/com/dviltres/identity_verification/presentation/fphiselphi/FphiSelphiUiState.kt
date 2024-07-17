package com.dviltres.identity_verification.presentation.fphiselphi

import com.dviltres.identity_verification.presentation.fphiselphi.model.FphiSelphiState


internal sealed class FphiSelphiUiState {
    object LoadingUiState : FphiSelphiUiState()
    data class SelphiUiState(val state: FphiSelphiState) : FphiSelphiUiState()
}
