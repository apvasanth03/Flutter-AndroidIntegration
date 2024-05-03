package io.github.apvasanth03.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.flutter.embedding.android.FlutterActivity
import io.github.apvasanth03.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier.align(alignment = Alignment.Center),
                            onClick = {
                                navigateToFlutterScreen()
                            }) {
                            Text("Navigate to Flutter Screen")

                        }
                    }
                }
            }
        }
    }

    private fun navigateToFlutterScreen() {
        // Navigate to Flutter Screen
        startActivity(
            FlutterActivity.createDefaultIntent(this)
        )
    }
}