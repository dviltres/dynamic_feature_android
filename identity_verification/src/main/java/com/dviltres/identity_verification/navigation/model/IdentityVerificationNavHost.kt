package com.dviltres.identity_verification.navigation.model

import android.content.Context
import androidx.navigation.NavHostController
import com.dviltres.identity_verification.navigation.IdentityVerificationNavActions
import com.dviltres.identity_verification.presentation.FphiSelphiViewModel

internal data class IdentityVerificationNavHost(
    val startDestination: String,
    val navActions: IdentityVerificationNavActions,
    val navController: NavHostController,
    val fphiSelphiViewModel: FphiSelphiViewModel,
    val context: Context
)
