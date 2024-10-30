package com.example.usecamara

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.Manifest
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.core.content.ContextCompat
import com.example.usecamara.ui.theme.UseCamaraTheme
import com.example.usecamara.ui.views.CameraPreviewScreen


class MainActivity : ComponentActivity() {
    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                setCameraPreview()
            } else {

            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                //camera permission alredy granted
                //implement camera related code
                setCameraPreview()
            }

            else -> {
                cameraPermissionRequest.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    private fun setCameraPreview() {
        enableEdgeToEdge()
        setContent {
            UseCamaraTheme {
                Scaffold(
                    modifier =
                    Modifier.fillMaxSize()
                ) { innerPadding ->
                    CameraPreviewScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


