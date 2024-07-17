package com.dviltres.identity_verification.navigation

import androidx.navigation.NavHostController

internal class IdentityVerificationNavActions(navHostController: NavHostController) {
    val fphiSelphi: () -> Unit = {
        navHostController.navigate(route = IdentityVerificationRoutes.FphiSelphi.path)
    }
}
