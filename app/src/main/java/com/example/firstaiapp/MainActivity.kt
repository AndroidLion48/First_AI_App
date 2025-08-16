package com.example.firstaiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Main Activity using ComponentActivity for Jetpack Compose
 * Follows MVVM pattern with ViewModel handling business logic
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display
        setContent {
            FirstAIAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AIAppScreen() // Main UI screen
                }
            }
        }
    }
}

/**
 * Main UI screen composable following MVVM pattern
 * Observes ViewModel state and delegates user actions to ViewModel
 * 
 * @param viewModel The AIViewModel instance (automatically provided by viewModel())
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIAppScreen(viewModel: AIViewModel = viewModel()) {
    // Collect and observe UI state from ViewModel
    // UI automatically recomposes when state changes
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Allow scrolling for long responses
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top spacing for edge-to-edge display
        Spacer(modifier = Modifier.height(34.dp))

        // App title
        Text(
            text = "Clarence's First AI App",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
        )

        // User input field
        // Disabled during loading to prevent input while processing
        OutlinedTextField(
            value = uiState.prompt,
            onValueChange = { viewModel.updatePrompt(it) }, // Delegate to ViewModel
            label = { Text("Enter your prompt here") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        )

        // Submit button with loading indicator
        // Disabled when loading or when prompt is empty
        Button(
            onClick = { viewModel.generateResponse() }, // Delegate to ViewModel
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading && uiState.prompt.isNotBlank()
        ) {
            // Show loading indicator when processing
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(if (uiState.isLoading) "Processing..." else "Submit")
        }

        // Response display card
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = uiState.result,
                fontSize = 14.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/**
 * Custom theme wrapper using Material 3 design
 * Applies light color scheme to the entire app
 */
@Composable
fun FirstAIAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        content = content
    )
}

/**
 * Preview function for the main screen
 * Allows viewing the UI in Android Studio's design preview
 */
@Preview(showBackground = true)
@Composable
fun AIAppScreenPreview() {
    FirstAIAppTheme {
        AIAppScreen()
    }
}
