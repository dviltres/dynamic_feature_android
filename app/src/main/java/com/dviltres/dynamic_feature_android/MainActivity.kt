package com.dviltres.dynamic_feature_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dviltres.dynamic_feature_android.ui.theme.DynamicFeatureTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicFeatureTheme {
                DemoScreen(
                    onClickDefaultUI = {
                        Toast.makeText(applicationContext, "Starting on demand module", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
