package com.dviltres.identity_verification.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dviltres.identity_verification.di.IdentityVerificationDependencies
import com.dviltres.identity_verification.di.IdentityVerificationSource
import com.dviltres.identity_verification.navigation.model.IdentityVerificationNavHost
import com.dviltres.identity_verification.presentation.FphiSelphiViewModel
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.ERROR_MESSAGE
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.RESULT_NOT_PERMISSIONS
import com.dviltres.identity_verification.ui.fphiselphi.constants.Constants.RESULT_NOT_PROCESSED
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IdentityVerificationNavGraph(
    startDestination: String = IdentityVerificationRoutes.FphiSelphi.path,
    dependencies: IdentityVerificationDependencies
) {
    val navController = rememberAnimatedNavController()
    val navActions = remember(navController) { IdentityVerificationNavActions(navController) }
    val source = IdentityVerificationSource()

    val fphiSelphiViewModel: FphiSelphiViewModel = viewModel()

    IdentityVerificationNavHostCompose(
        navHost = IdentityVerificationNavHost(
            startDestination = startDestination,
            navActions = navActions,
            navController = navController,
            fphiSelphiViewModel = fphiSelphiViewModel,
            context = dependencies.appContext
        )
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun IdentityVerificationNavHostCompose(navHost: IdentityVerificationNavHost) {
    val activity = (LocalContext.current as? Activity)
    AnimatedNavHost(
        navController = navHost.navController,
        startDestination = navHost.startDestination
    ) {
        fphiSelphiNav(
            onSuccess = returnCallbackSuccess(activity),
            onNotPermissionEvent = returnCallbackNotPermissions(activity),
            onNotProcessedEvent = returnCallbackNotProcessed(activity),
            viewModel = navHost.fphiSelphiViewModel
        )
    }
}

private fun returnCallbackNotProcessed(activity: Activity?): (String) -> Unit = { error->
    activity?.let { currentActivity ->
        with(currentActivity) {
            val resultIntent = Intent().apply {
                putExtra(ERROR_MESSAGE,error)
            }
            setResult(RESULT_NOT_PROCESSED, resultIntent)
            finish()
        }
    }
}

private fun returnCallbackNotPermissions(activity: Activity?): () -> Unit = {
    activity?.let { currentActivity ->
        with(currentActivity) {
            setResult(RESULT_NOT_PERMISSIONS)
            finish()
        }
    }
}

private fun returnCallbackSuccess(activity: Activity?): () -> Unit = {
    activity?.let { currentActivity ->
        with(currentActivity) {
            finish()
        }
    }
}
