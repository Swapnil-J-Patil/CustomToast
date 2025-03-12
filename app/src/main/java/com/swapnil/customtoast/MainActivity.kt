package com.swapnil.customtoast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swapnil.customtoast.ui.theme.CustomToastTheme
import com.swapnil.customtoast.ui.theme.blue
import com.swapnil.customtoast.ui.theme.green
import com.swapnil.customtoast.ui.theme.red
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomToastTheme {
                var showToast by remember { mutableStateOf(false) }
                var toastMessage by remember { mutableStateOf("Success!") }
                var progressBarColor by remember { mutableStateOf(red) }
                var borderColor by remember { mutableStateOf(Color.Transparent) }
                var backgroundColor by remember { mutableStateOf(Color.White) }
                var durationMillis by remember { mutableStateOf(3000L) }
                var imageVector by remember { mutableStateOf(Icons.Default.Warning) }
                val coroutineScope = rememberCoroutineScope()

                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 46.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            showToast = false // Dismiss current toast
                            coroutineScope.launch { // Ensure state updates properly
                                delay(100) // Small delay to allow recomposition
                                toastMessage = "Operation Completed!"
                                progressBarColor = green
                                borderColor = Color.Transparent
                                backgroundColor = Color.White
                                durationMillis = 3000L
                                imageVector = Icons.Default.CheckCircle
                                showToast = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = green),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text = "Show Success Toast",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = {
                            showToast = false // Dismiss current toast
                            coroutineScope.launch { // Ensure state updates properly
                                delay(100) // Small delay to allow recomposition
                                toastMessage = "An error occurred!"
                                progressBarColor = red
                                borderColor = Color.Transparent
                                backgroundColor = Color.White
                                durationMillis = 3000L
                                imageVector = Icons.Default.Warning
                                showToast = true
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = red),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            "Show Error Toast",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            coroutineScope.launch { // Ensure state updates properly
                                showToast = false // Dismiss current toast
                                delay(100) // Small delay to allow recomposition
                                toastMessage = "Information Toast!"
                                progressBarColor = blue
                                borderColor = Color.Transparent
                                backgroundColor = Color.White
                                durationMillis = 3000L
                                imageVector = Icons.Default.Info
                                showToast = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = blue),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            "Show Information Toast",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }

                    // Show toast when triggered

                    CustomToast(
                        message = toastMessage,
                        onDismiss = { showToast = !showToast },
                        borderColor = borderColor,
                        backgroundColor = backgroundColor,
                        textColor = Color.Black,
                        imageVector = imageVector,
                        durationMillis = durationMillis,
                        progressBarColor = progressBarColor,
                        visibility = showToast,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .background(Color.Transparent),
                        alignment = Alignment.BottomCenter
                    )
                }
            }
        }
    }
}

