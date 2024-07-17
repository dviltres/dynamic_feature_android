package com.dviltres.identity_verification.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.dviltres.identity_verification.di.IdentityVerificationDependencies
import com.dviltres.identity_verification.navigation.IdentityVerificationNavGraph

class IdentityVerificationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                IdentityVerificationNavGraph(
                    dependencies = IdentityVerificationDependencies(
                        appContext = this
                    )
                )
            }
        }
    }
    companion object {
        const val RESULT_NOT_PROCESSED = -3
    }
}