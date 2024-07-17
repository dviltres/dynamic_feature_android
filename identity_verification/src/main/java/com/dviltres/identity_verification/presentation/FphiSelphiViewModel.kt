package com.dviltres.identity_verification.presentation

import androidx.lifecycle.ViewModel
import com.dviltres.identity_verification.presentation.fphiselphi.FphiSelphiUiState
import com.dviltres.identity_verification.presentation.fphiselphi.model.FphiSelphiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class FphiSelphiViewModel constructor() : ViewModel() {
    val defaultUiState: FphiSelphiUiState = FphiSelphiUiState.LoadingUiState
    private val _uiState: MutableStateFlow<FphiSelphiUiState> = MutableStateFlow(defaultUiState)

    fun startSelphi() {
        _uiState.value = FphiSelphiUiState.SelphiUiState(FphiSelphiState())
    }

    fun getUiState(): StateFlow<FphiSelphiUiState> = _uiState
}
