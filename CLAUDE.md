# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android application that demonstrates AI integration using Google's Gemini API. The app provides a simple interface where users can enter prompts and receive AI-generated responses through the Gemini 2.5 Flash model.

## Architecture

- **Single Activity Architecture**: Uses `MainActivity.kt` (ComponentActivity) as the primary entry point
- **Jetpack Compose UI**: Modern declarative UI using Material 3 components
- **Asynchronous AI Integration**: Uses coroutines with proper scope management for Gemini API calls
- **State Management**: Uses Compose state management with `remember` and `mutableStateOf`
- **Direct API Integration**: Gemini API key is managed through the secrets plugin system

## Development Commands

### Build and Run
```bash
# Build the project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install debug build to connected device
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Code Quality
```bash
# Lint checks
./gradlew lint

# Generate lint report
./gradlew lintDebug
```

## Key Configuration

- **Namespace**: `com.example.firstaiapp`
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 36
- **Compile SDK**: 36
- **Java Version**: 11
- **Kotlin**: 2.0.21
- **Android Gradle Plugin**: 8.12.0

## API Key Management

The project uses the Google Maps Platform Secrets Gradle Plugin to manage the Gemini API key:
- API key is stored in `secrets.properties` 
- **Security Note**: The current implementation has a hardcoded API key in `MainActivity.kt:31` which should be replaced with proper secrets management
- Proper implementation should use `BuildConfig.API_KEY` instead of hardcoded strings

## Dependencies

Key dependencies include:
- `com.google.ai.client.generativeai:generativeai:0.9.0` - Gemini AI client
- Jetpack Compose BOM and core libraries (UI, Material 3, Activity Compose)
- Standard Android libraries (Core KTX, AppCompat)
- Testing libraries (JUnit, Espresso, Compose UI Test)

## File Structure

- `app/src/main/java/com/example/firstaiapp/MainActivity.kt` - Main application logic with Compose UI and AI integration
- `app/build.gradle.kts` - App-level build configuration with Compose setup
- `build.gradle.kts` - Project-level build configuration  
- `gradle/libs.versions.toml` - Centralized dependency version management including Compose libraries
- `secrets.properties` - API key storage (should not be committed to version control)

## Compose UI Components

The app uses the following Compose components:
- `AIAppScreen` - Main composable containing the entire UI
- `FirstAIAppTheme` - Material 3 theme wrapper
- Uses modern Material 3 components like `OutlinedTextField`, `Card`, and `CircularProgressIndicator`
- Includes proper loading states and error handling