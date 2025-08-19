package com.example.firstaiapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Data class representing the UI state of the AI app
 * Contains all the state that the UI needs to display
 */
data class AIUiState(
    val prompt: String = "",                           // User input text
    val result: String = "AI Response Displays Here",  // AI response text
    val isLoading: Boolean = false,                    // Loading state for API calls
    val error: String? = null                          // Error message if API call fails
)

/**
 * ViewModel for the AI app following MVVM architecture pattern
 * Manages the business logic and state for AI interactions
 * Survives configuration changes and provides clean separation of concerns
 */
class AIViewModel : ViewModel() {

    // Private mutable state flow that only the ViewModel can modify
    private val _uiState = MutableStateFlow(AIUiState())

    // Public read-only state flow that the UI observes for state changes
    val uiState: StateFlow<AIUiState> = _uiState.asStateFlow()

    // Initialize the Gemini AI model
    // TODO: Move API key to BuildConfig or secure storage
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = "API_KEY_GOES_HERE" // Replace with your actual API key
    )

    /**
     * Updates the user's prompt text in the UI state
     * Also clears any existing error when user types
     */
    fun updatePrompt(prompt: String) {
        _uiState.value = _uiState.value.copy(prompt = prompt, error = null)
    }

    /**
     * Generates AI response from the current prompt
     * Handles loading states and error handling
     * Uses viewModelScope for automatic coroutine lifecycle management
     */
    fun generateResponse() {
        val currentPrompt = _uiState.value.prompt
        if (currentPrompt.isBlank()) return

        // Set loading state to true and clear any previous errors
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        // Launch coroutine in viewModelScope (automatically cancelled when ViewModel is destroyed)
        viewModelScope.launch {
            try {
                // Make API call to generate AI response
                val response = generativeModel.generateContent(currentPrompt)

                // Update state with successful response
                _uiState.value = _uiState.value.copy(
                    result = response.text ?: "No response received",
                    isLoading = false
                )
            } catch (e: Exception) {
                // Handle API errors and update state accordingly
                _uiState.value = _uiState.value.copy(
                    result = "Error: ${e.message}",
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    /**
     * Clears any error state
     * Useful for dismissing error messages in the UI
     */
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
