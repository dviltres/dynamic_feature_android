package com.dviltres.identity_verification.navigation

sealed class IdentityVerificationRoutes(val path: String) {
    object FphiSelphi : IdentityVerificationRoutes("FphiSelphiScreen")
}
